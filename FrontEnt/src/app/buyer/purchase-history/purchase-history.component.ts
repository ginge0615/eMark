import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/common/interface/Item';
import { GlobalService } from 'src/app/common/service/global.service';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {
  constructor(private global: GlobalService) { }
  listOfData : Item[];

  ngOnInit() {
  }

}
