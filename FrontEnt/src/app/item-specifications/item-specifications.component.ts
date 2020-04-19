import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-item-specifications',
  templateUrl: './item-specifications.component.html',
  styleUrls: ['./item-specifications.component.css']
})
export class ItemSpecificationsComponent implements OnInit {
  array = ['../../assets/pictures/samsung1.jpg','../../assets/pictures/samsung2.jpg','../../assets/pictures/samsung3.jpg','../../assets/pictures/samsung4.jpg'];
  effect = 'scrollx';

  //test
  item : string = "Oppo A5S";
  price : string =  "$18,555";
  seller : string = "Seller2";
  salesVolume : number = 200;
  buyNum : number = 5;

  constructor() { }

  ngOnInit() {
  }

}
