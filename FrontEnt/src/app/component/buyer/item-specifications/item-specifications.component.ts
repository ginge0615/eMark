import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/models/Item';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'src/app/services/message.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ItemService } from 'src/app/services/item.service';
import { Option } from 'src/app/models/option';
import { GlobalService } from 'src/app/services/global.service';
import { CartService } from 'src/app/services/cart.service';


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
  data: Item;
  descriptions: Option[] = [];

  constructor(private fb: FormBuilder,
    private routerInfo: ActivatedRoute,
    private msgService: MessageService,
    private msgPopup: NzMessageService,
    private itemService: ItemService,
    private router: Router,
    private globalService: GlobalService,
    private cartService: CartService
  ) {
    this.msgService.hideMessage();
  }

  goBack() {
    history.go(-1);
  }

  addToCart() {
    if (!this.globalService.isLogined()) {
      this.msgPopup.warning("Please login first.");
      return;
    }

    let hasError : boolean = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }

    if (hasError) return;

    this.cartService.addToCart(this.globalService.getUserId(), this.data.id, this.data.purchaseNum).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.msgPopup.success("Successful add to cart.");
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error', response.status]);
      }
    );
  }

  submitForm(): void {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      purchaseNum: [null, [Validators.required]],
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

}
