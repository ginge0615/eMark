import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/common/Item';
import { GlobalService } from 'src/app/common/global.service';

@Component({
  selector: 'app-item-specifications',
  templateUrl: './item-specifications.component.html',
  styleUrls: ['./item-specifications.component.css']
})
export class ItemSpecificationsComponent implements OnInit {
  effect = 'scrollx';

  id : string;
  num : number = 1;
  data : Item;

  constructor(private routerInfo: ActivatedRoute, private global: GlobalService) { }

  ngOnInit() {
    this.routerInfo.paramMap.subscribe(params => {
      this.id= params.get('id');
    });

    if (this.id) {
      for (let data of this.global.listItems) {
        if (data.id === this.id) {
            this.data = data;
        }
      }
    }
  }

}
