import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/common/service/global.service';

@Component({
  selector: 'app-buyer-main',
  templateUrl: './buyer-main.component.html',
  styleUrls: ['./buyer-main.component.css'],
})
export class BuyerMainComponent implements OnInit {

  constructor(public global: GlobalService) { }

  ngOnInit() {
  }

}
