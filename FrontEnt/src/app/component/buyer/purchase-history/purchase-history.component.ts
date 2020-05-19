import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/models/Item';
import { GlobalService } from 'src/app/services/global.service';

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
