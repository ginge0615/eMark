import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/common/Item';
import { GlobalService } from 'src/app/common/global.service';

@Component({
  selector: 'app-item-specifications',
  templateUrl: './item-specifications.component.html',
  styleUrls: ['./item-specifications.component.css']
})
export class ItemSpecificationsComponent implements OnInit {
  effect = 'scrollx';

  id: string;
  num: number = 1;
  data: Item;

  constructor(private routerInfo: ActivatedRoute, private global: GlobalService) { }

  goBack() {
    history.go(-1);
  }

  addToCart() {
    if (this.global.cartItems?.length > 0) {
      for (let item of this.global.cartItems) {
        if (item.id === this.data.id) {
          item.purchaseNum += this.num;
          return;
        }
      }
    }

    this.data.purchaseNum = this.num;
    this.global.cartItems.push(this.data);
  }

  ngOnInit() {
    this.routerInfo.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    if (this.id) {
      for (let data of this.global.listItems) {
        if (data.id === this.id) {
          this.data = data;
        }
      }
    }
  }

}
