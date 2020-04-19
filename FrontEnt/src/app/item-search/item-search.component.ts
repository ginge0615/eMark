import { Component, OnInit } from '@angular/core';

interface DataItem {
  picture: string;
  subcategory : string;
  item: string;
  price: number;
  seller: string;
  salesvolume :number;
}

@Component({
  selector: 'app-item-search',
  templateUrl: './item-search.component.html',
  styleUrls: ['./item-search.component.css']
})
export class ItemSearchComponent implements OnInit {

  listOfColumn = [
    {
      title: ''
    },
    {
      title: 'Item',
      compare: (a: DataItem, b: DataItem) => a.subcategory.localeCompare(b.subcategory) != 0 ? a.subcategory.localeCompare(b.subcategory): a.item.localeCompare(b.item),
      priority: 3
    },
    {
      title: 'Price',
      compare: (a: DataItem, b: DataItem) => a.price - b.price,
      priority: 2
    },
    {
      title: 'Seller',
      compare: (a: DataItem, b: DataItem) => a.seller.localeCompare(b.seller),
      priority: 4
    },
    {
      title: 'Sales volume',
      compare: (a: DataItem, b: DataItem) => a.salesvolume - b.salesvolume,
      priority: 1
    },
    {
      title: ''
    }
  ];

  listOfData: DataItem[] = [
    {
      picture: 'picture1',
      subcategory : 'Samsung',
      item:'Galaxy s7',
      price: 123456,
      seller: 'Seller1',
      salesvolume : 100
    },
    {
      picture: 'picture2',
      subcategory : 'OPPO',
      item:'A5S',
      price: 8838,
      seller: 'Seller2',
      salesvolume : 200
    },
    {
      picture: 'picture3',
      subcategory : 'Samsung',
      item:'Galaxy s6',
      price: 5800,
      seller: 'Seller2',
      salesvolume : 300
    },
    {
      picture: 'picture1',
      subcategory : 'Samsung',
      item:'Galaxy s7',
      price: 123456,
      seller: 'Seller1',
      salesvolume : 100
    },
    {
      picture: 'picture2',
      subcategory : 'OPPO',
      item:'A5S',
      price: 8838,
      seller: 'Seller2',
      salesvolume : 200
    },
    {
      picture: 'picture3',
      subcategory : 'Samsung',
      item:'Galaxy s6',
      price: 5800,
      seller: 'Seller2',
      salesvolume : 300
    },
    {
      picture: 'picture1',
      subcategory : 'Samsung',
      item:'Galaxy s7',
      price: 123456,
      seller: 'Seller1',
      salesvolume : 100
    },
    {
      picture: 'picture2',
      subcategory : 'OPPO',
      item:'A5S',
      price: 8838,
      seller: 'Seller2',
      salesvolume : 200
    },
    {
      picture: 'picture3',
      subcategory : 'Samsung',
      item:'Galaxy s6',
      price: 5800,
      seller: 'Seller2',
      salesvolume : 300
    }
    
  ];

  constructor() { }

  ngOnInit() {
  }

}
