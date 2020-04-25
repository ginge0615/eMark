import { Component, OnInit } from '@angular/core';

interface ItemData {
  id: number;
  picture: string;
  subcategory : string;
  item: string;
  price: number;
  num: number;
  datetime : Date;
}

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {

  listOfData: ItemData[] = [
    {
      id : 1,
      picture : '../../assets/pictures/samsung1.jpg',
      subcategory : 'Samsung',
      item : 'Galaxy s7',
      price: 123456,
      num : 1,
      datetime : new Date()
    },
    {
      id : 2,
      picture : '../../assets/pictures/oppo1.jpg',
      subcategory : 'OPPO',
      item : 'A5S',
      price: 8838,
      num : 1,
      datetime : new Date()
    }

  ];
  
  constructor() { }

  ngOnInit() {
    console.info("PurchaseHistoryComponent Actived!!!!!");
  }

}
