import { Component, OnInit } from '@angular/core';

interface ItemData {
  id: number;
  picture: string;
  subcategory : string;
  item: string;
  price: number;
  stock: number;
}

@Component({
  selector: 'app-manage-stock',
  templateUrl: './manage-stock.component.html',
  styleUrls: ['./manage-stock.component.css']
})
export class ManageStockComponent implements OnInit {

  listOfData: ItemData[] = [
    {
      id : 1,
      picture : '../../assets/pictures/samsung1.jpg',
      subcategory : 'Samsung',
      item : 'Galaxy s7',
      price: 123456,
      stock : 100,
    },
    {
      id : 2,
      picture : '../../assets/pictures/oppo1.jpg',
      subcategory : 'OPPO',
      item : 'A5S',
      price: 8838,
      stock : 200,
    }

  ];
  constructor() { }

  ngOnInit() {
  }

}
