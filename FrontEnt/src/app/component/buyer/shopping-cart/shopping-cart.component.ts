import { Component, OnInit } from '@angular/core';
import { CartModel } from 'src/app/models/CartModel';
import { GlobalService } from 'src/app/services/global.service';
import { CartService } from 'src/app/services/cart.service';
import { TransactionService } from 'src/app/services/transaction.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {DiscountService} from 'src/app/services/discount.service'

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  allChecked = false;
  indeterminate = false;
  totalTax: number = 0;
  totalPrice: number = 0;
  setOfCheckedId = new Set<string>();
  listOfData: CartModel[] = [];
  isDiscountVisible : boolean = false;
  discountCode : string;
  validateForm: FormGroup;

  private userId: string;

  constructor(private globalService: GlobalService,
    private cartService: CartService,
    private transactionService: TransactionService,
    private router: Router,
    private msgPopup: NzMessageService,
    private fb: FormBuilder,
    private discountService : DiscountService) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      discount: [null, [Validators.required]],
    });

    this.userId = this.globalService.getUserId();

    this.cartService.getCart(this.userId).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.listOfData = respData;
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error', response.status]);
      }
    );
  }

  updateCheckedSet(data: CartModel, checked: boolean): void {
    if (checked) {
      this.setOfCheckedId.add(data.id);
    } else {
      this.setOfCheckedId.delete(data.id);
    }
  }

  updateTotalPriceSum() {
    this.totalPrice = 0;
    this.totalTax = 0;

    for (let item of this.listOfData) {
      if (this.setOfCheckedId.has(item.id)) {
        this.totalPrice += (item.price + item.tax) * item.number;
        this.totalTax += item.number * item.tax;
      }
    }

  }

  onItemChecked(data: CartModel, checked: boolean): void {
    this.updateCheckedSet(data, checked);
    this.refreshCheckedStatus();
  }

  onAllChecked(checked: boolean): void {
    this.listOfData.forEach(item => this.updateCheckedSet(item, checked));
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.allChecked = this.listOfData.every(item => this.setOfCheckedId.has(item.id));
    this.indeterminate = this.listOfData.some(item => this.setOfCheckedId.has(item.id)) && !this.allChecked;
    this.updateTotalPriceSum();
  }


  deleteRow(id: string) {
    this.cartService.delete(id).subscribe(
      data => {
        const respData: any = data;
        this.listOfData = this.listOfData.filter(d => d.id !== id);
        this.cartService.refreshCount(this.userId);
        this.msgPopup.success("Successful deleted!");
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error', response.status]);
      }
    );
  }

  checkout(): void {
    if (this.setOfCheckedId.size === 0) {
      this.msgPopup.error("Please select the item for checkout.");
      return;
    }

    let models = [];

    for (let data of this.listOfData) {
      if (this.setOfCheckedId.has(data.id)) {
        models.push(
          {
            buyerId: this.userId,
            sellerId: data.sellerId,
            itemId: data.itemId,
            price: data.price + data.tax,
            purchaseNumber: data.number,
            type: "1",
            transactionAmount: (data.price + data.tax) * data.number
          }
        );
      }
    }

    this.transactionService.checkout(models).subscribe(
      data => {
        //successful
        const respData: any = data;
        let hasError : boolean = false;

        for (let id of this.setOfCheckedId.values()) {
          this.cartService.delete(id).subscribe(
            data => {
              const respData: any = data;
              this.listOfData = this.listOfData.filter(d => d.id !== id);
            },
            res => {
              //error
              const response: any = res;
              hasError = true;
            }
          );
        }

        this.cartService.refreshCount(this.userId);

        if (!hasError) {
          this.setOfCheckedId.clear();
          this.router.navigate(['/checkout-success']);
        }else {
          this.router.navigate(['/server-error', 500]);
        }
      },
      res => {
        //error
        const response: any = res;

        if (response.status === 406) {
          this.msgPopup.error(response.error.message);
        } else {
          this.router.navigate(['/server-error', response.status]);
        }
      }
    );

  }

  showModal(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }

    this.isDiscountVisible = true;
  }

  handleOk(): void {
    let hasError : boolean = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }

    if (hasError) return;

    this.isDiscountVisible = false;

    this.discountService.getDiscount(this.discountCode).subscribe(
      data => {
        const respData: any = data;

        let discountValue = Number(respData);

        for (let item of this.listOfData) {
          item.price = item.price * (1 - discountValue);
          item.tax = item.tax * (1 - discountValue);
        }

        this.msgPopup.success("All items are sold " + (discountValue * 100) +"% off! ");
      },
      res => {
        //error
        const response: any = res;
        this.msgPopup.warning("The discount code is incorrect or expired.");
      }
    );
    
  }

  handleCancel(): void {
    this.isDiscountVisible = false;
  }
}
