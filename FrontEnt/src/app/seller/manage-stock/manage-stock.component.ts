import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/common/interface/Item';
import { GlobalService } from 'src/app/common/service/global.service';

@Component({
  selector: 'app-manage-stock',
  templateUrl: './manage-stock.component.html',
  styleUrls: ['./manage-stock.component.css']
})
export class ManageStockComponent implements OnInit {

  listOfData: Item[];
  constructor(private global: GlobalService) {
  }

  ngOnInit() {
  }

}
