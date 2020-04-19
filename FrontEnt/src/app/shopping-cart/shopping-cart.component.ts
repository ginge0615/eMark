import { Component, OnInit } from '@angular/core';

interface ItemData {
  id: number;
  picture: string;
  subcategory : string;
  item: string;
  price: number;
  num: number;
}

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  checked = false;
  indeterminate = false;
  listOfCurrentPageData: ItemData[] = [];
  setOfCheckedId = new Set<number>();
  listOfData: ItemData[] = [
    {
      id : 1,
      picture : '../../assets/pictures/samsung1.jpg',
      subcategory : 'Samsung',
      item : 'Galaxy s7',
      price: 123456,
      num : 1
    },
    {
      id : 1,
      picture : '../../assets/pictures/oppo1.jpg',
      subcategory : 'OPPO',
      item : 'A5S',
      price: 8838,
      num : 1
    }

  ];

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.setOfCheckedId.add(id);
    } else {
      this.setOfCheckedId.delete(id);
    }
  }

  onItemChecked(id: number, checked: boolean): void {
    this.updateCheckedSet(id, checked);
    this.refreshCheckedStatus();
  }

  onAllChecked(value: boolean): void {
    this.listOfCurrentPageData.forEach(item => this.updateCheckedSet(item.id, value));
    this.refreshCheckedStatus();
  }

  onCurrentPageDataChange($event: ItemData[]): void {
    this.listOfCurrentPageData = $event;
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.listOfCurrentPageData.every(item => this.setOfCheckedId.has(item.id));
    this.indeterminate = this.listOfCurrentPageData.some(item => this.setOfCheckedId.has(item.id)) && !this.checked;
  }

  ngOnInit(): void {
    // this.listOfData = new Array(200).fill(0).map((_, index) => {
    //   return {
    //     id: index,
    //     name: `Edward King ${index}`,
    //     age: 32,
    //     address: `London, Park Lane no. ${index}`
    //   };
    // });
  }
}
