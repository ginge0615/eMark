import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/models/Item';
import { ItemService } from 'src/app/services/item.service';
import { GlobalService } from 'src/app/services/global.service';
import { MessageService } from 'src/app/services/message.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-manage-stock',
  templateUrl: './manage-stock.component.html',
  styleUrls: ['./manage-stock.component.css']
})
export class ManageStockComponent implements OnInit {
  baseUrl : string = environment.baseUrl;
  listOfData: Item[];
  constructor(private itemService: ItemService, 
    private globalService : GlobalService,
    private msgService: MessageService,
    private msgPopup: NzMessageService,
    private router: Router
    ) {
  }

  ngOnInit() {
    this.itemService.getAllItemsBySeller(this.globalService.getUserId()).subscribe(
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

  update(data : Item) {
    this.itemService.updateStock(data.id, data.stock).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.msgPopup.success("Successful update stock.");
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error',response.status]);
      }
    );
  }

}
