import { Component, OnInit } from '@angular/core';

interface ItemData {
  id: number;
  picture: string;
  subcategory : string;
  item: string;
  price: number;
  num: number;
  tax : number;
}

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
  setOfCheckedId = new Set<number>();
  listOfData: ItemData[] = [
    {
      id : 1,
      picture : '../../assets/pictures/samsung1.jpg',
      subcategory : 'Samsung',
      item : 'Galaxy s7',
      price: 123456,
      num : 1,
      tax : 0.01
    },
    {
      id : 2,
      picture : '../../assets/pictures/oppo1.jpg',
      subcategory : 'OPPO',
      item : 'A5S',
      price: 8838,
      num : 1,
      tax : 0.01
    }

  ];

  updateCheckedSet(data : ItemData, checked: boolean): void {
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
        this.totalPrice += item.price * item.num * (1 + item.tax);
        this.totalTax += item.price * item.num * item.tax;
      }
    }
   
  }

  onItemChecked(data : ItemData, checked: boolean): void {
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

  ngOnInit(): void {

  }
}
