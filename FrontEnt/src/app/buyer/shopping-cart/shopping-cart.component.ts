import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/common/Item';
import { GlobalService } from 'src/app/common/global.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  allChecked  = false;
  indeterminate = false;
  totalTax : number = 0;
  totalPrice : number = 0;
  setOfCheckedId = new Set<string>();
  listOfData: Item[] = this.global.cartItems;

  constructor(private global: GlobalService) {
  }

  updateCheckedSet(data : Item, checked: boolean): void {
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
        this.totalPrice += item.price * item.purchaseNum * (1 + item.tax);
        this.totalTax += item.price * item.purchaseNum * item.tax;
      }
    }
   
  }

  onItemChecked(data : Item, checked: boolean): void {
    this.updateCheckedSet(data, checked);
    this.refreshCheckedStatus();
  }

  onAllChecked(checked: boolean): void {
    this.listOfData.forEach(item => this.updateCheckedSet(item, checked));
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.allChecked  = this.listOfData.every(item => this.setOfCheckedId.has(item.id));
    this.indeterminate = this.listOfData.some(item => this.setOfCheckedId.has(item.id)) && !this.allChecked ;
    this.updateTotalPriceSum();
  }
  

  deleteItem(index : number) {
    this.global.cartItems.splice(index, 1);
    document.getElementById("tr" + index).style.display = "none";
  }

  ngOnInit(): void {

  }
}
