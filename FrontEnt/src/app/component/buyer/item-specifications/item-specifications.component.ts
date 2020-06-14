import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ItemDetail } from 'src/app/models/ItemDetail';
import { MessageService } from 'src/app/services/message.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ItemService } from 'src/app/services/item.service';
import { Option } from 'src/app/models/option';
import { GlobalService } from 'src/app/services/global.service';
import { CartService } from 'src/app/services/cart.service';
import { TransactionService } from 'src/app/services/transaction.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-item-specifications',
  templateUrl: './item-specifications.component.html',
  styleUrls: ['./item-specifications.component.css']
})
export class ItemSpecificationsComponent implements OnInit {
  validateForm: FormGroup;
  effect = 'scrollx';

  id: string;
  num: number = 1;
  data: ItemDetail;
  descriptions: Option[] = [];
  baseUrl : string = environment.baseUrl;

  constructor(
    private routerInfo: ActivatedRoute,
    private msgService: MessageService,
    private msgPopup: NzMessageService,
    private itemService: ItemService,
    private router: Router,
    private globalService: GlobalService,
    private cartService: CartService,
    private transactionService : TransactionService,
    private fb: FormBuilder
  ) {
    this.msgService.hideMessage();
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      number: [null, [Validators.required]],
    });

    this.routerInfo.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    if (this.id) {
      this.itemService.viewDetail(this.id).subscribe(
        data => {
          //successful
          const respData: any = data;
          this.data = respData;

          //set descriptions
          for (let description of this.data.descriptions) {
            let option = description.split(":");

            if (option.length >= 2) {
              this.descriptions.push({
                label: option[0],
                value: option[1]
              });
            } else {
              this.descriptions.push({
                label: option[0],
                value: ""
              });
            }
          }

        },
        res => {
          //error
          const response: any = res;
          this.router.navigate(['/server-error', response.status]);
        }
      );
    }
  }

  goBack() {
    history.go(-1);
  }

  addToCart() {
    if (!this.check()) return;

    let model = {
      buyerId: this.globalService.getUserId(),
      itemId: this.data.id,
      number: this.data.number
    }

    this.cartService.addToCart(model).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.cartService.refreshCount(model.buyerId);
        this.msgPopup.success("Successful add to cart.");
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error', response.status]);
      }
    );
  }

  checkout(): void {
    if (!this.check()) return;

    let models = [{
      buyerId: this.globalService.getUserId(),
      sellerId: this.data.sellerId,
      itemId: this.data.id,
      price : this.data.price + this.data.tax,
      purchaseNumber: this.data.number,
      type : "1",
      transactionAmount : (this.data.price + this.data.tax) * this.data.number
    }];

    this.transactionService.checkout(models).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.router.navigate(['/checkout-success']);
      },
      res => {
        //error
        const response: any = res;

        if (response.status === 406) {
          this.msgService.showErrorMsg(response.error.message);
        } else {
          this.router.navigate(['/server-error', response.status]);
        }
      }
    );

  }

  private check() : boolean {
    if (!this.globalService.isLogined()) {
      this.router.navigate(['/login']);
      return false;
    }

    this.data.number = this.num;

    let hasError : boolean = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }

    return !hasError;
  }

  submitForm(): void {
  }
}
