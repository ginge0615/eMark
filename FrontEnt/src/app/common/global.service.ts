import { Injectable } from '@angular/core';
import { Item } from './Item';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {
  constructor() { }

  public isLogined() : boolean {
    if (window.sessionStorage["token"]) {
      return true;
    }

    return false;
  }

  public isBuyer() : boolean {
    return window.sessionStorage["role"] === "1";
  }

  public isSeller() : boolean {
    return window.sessionStorage["role"] === "2";
  }
 
  //test
  public listItems : Item[] = [
    {id : "1",
      category : "Electronic",
      subcategory : "Mobile",
      manufactur : "Samsung",
      item:'Galaxy s7',
      price: 1000,
      tax : 0.05,
      pictures: ['../../assets/pictures/samsung1.jpg', '../../assets/pictures/samsung2.jpg', '../../assets/pictures/samsung3.jpg', '../../assets/pictures/samsung4.jpg'],
      descriptions:[{label:'Screen Resolution', value:'1080p'}, {label:'Network Type', value: '4G'}, {label : 'Battery Capactity', value: '3000mA'},
                    {label:'Weight', value:'100g'}, {label:'Width', value: '300mm'}, {label : 'Height', value: '800mm'}],
      seller: "Seller1",
      stock : 500,
      volume : 100,
      datetime : new Date(),
      purchaseNum : 0
    },
    {id : "2",
      category : "Electronic",
      subcategory : "Mobile",
      manufactur : "OPPO",
      item:'A5S',
      price: 800,
      tax : 0.05,
      pictures: ['../../assets/pictures/oppo1.jpg', '../../assets/pictures/oppo2.jpg'],
      descriptions:[{label:'Screen Resolution', value:'1080p'}, {label:'Network Type', value: '4G'}, {label : 'Battery Capactity', value: '3000mA'}],
      seller: "Seller2",
      stock : 300,
      volume : 80,
      datetime : new Date(),
      purchaseNum : 0
    },
    {id : "3",
      category : "Electronic",
      subcategory : "Mobile",
      manufactur : "Samsung",
      item:'Galaxy s6',
      price: 900,
      tax : 0.05,
      pictures: ['../../assets/pictures/samsung2.jpg', '../../assets/pictures/samsung1.jpg', '../../assets/pictures/samsung3.jpg', '../../assets/pictures/samsung4.jpg'],
      descriptions:[{label:'Screen Resolution', value:'1080p'}, {label:'Network Type', value: '4G'}, {label : 'Battery Capactity', value: '3000mA'}],
      seller: "Seller1",
      stock : 400,
      volume : 120,
      datetime : new Date(),
      purchaseNum : 0
    },
    {id : "4",
      category : "Electronic",
      subcategory : "Mobile",
      manufactur : "OPPO",
      item:'A4S',
      price: 500,
      tax : 0.03,
      pictures: ['../../assets/pictures/oppo2.jpg', '../../assets/pictures/oppo1.jpg'],
      descriptions:[{label:'Screen Resolution', value:'1080p'}, {label:'Network Type', value: '4G'}, {label : 'Battery Capactity', value: '3000mA'}],
      seller: "Seller2",
      stock : 350,
      volume : 81,
      datetime : new Date(),
      purchaseNum : 0
    },
    {id : "5",
      category : "Electronic",
      subcategory : "Mobile",
      manufactur : "Samsung",
      item:'Galaxy s3',
      price: 700,
      tax : 0.05,
      pictures: ['../../assets/pictures/samsung3.jpg', '../../assets/pictures/samsung1.jpg', '../../assets/pictures/samsung2.jpg', '../../assets/pictures/samsung4.jpg'],
      descriptions:[{label:'Screen Resolution', value:'1080p'}, {label:'Network Type', value: '4G'}, {label : 'Battery Capactity', value: '3000mA'}],
      seller: "Seller1",
      stock : 400,
      volume : 120,
      datetime : new Date(),
      purchaseNum : 0
    },
    {id : "6",
      category : "Electronic",
      subcategory : "Mobile",
      manufactur : "Samsung",
      item:'Galaxy s2',
      price: 300,
      tax : 0.05,
      pictures: ['../../assets/pictures/samsung4.jpg', '../../assets/pictures/samsung1.jpg', '../../assets/pictures/samsung2.jpg', '../../assets/pictures/samsung3.jpg'],
      descriptions:[{label:'Screen Resolution', value:'1080p'}, {label:'Network Type', value: '4G'}, {label : 'Battery Capactity', value: '3000mA'}],
      seller: "Seller1",
      stock : 1000,
      volume : 30,
      datetime : new Date(),
      purchaseNum : 0
    },

  ];

  public cartItems : Item[] = [];

}
