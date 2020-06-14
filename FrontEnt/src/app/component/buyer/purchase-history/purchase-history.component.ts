import { Component, OnInit } from '@angular/core';
import { PurchaseHistory } from 'src/app/models/PurchaseHistory';
import { GlobalService } from 'src/app/services/global.service';
import { HistoryService } from 'src/app/services/history.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {
  baseUrl : string = environment.baseUrl;

  constructor(private globalService: GlobalService, 
    private historyService:HistoryService,
    private router : Router) { }
  listOfData : PurchaseHistory[];

  ngOnInit() {
    this.historyService.getPuchaseHistory(this.globalService.getUserId()).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.listOfData = respData;
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error',response.status]);
      }
    );
  }

}
