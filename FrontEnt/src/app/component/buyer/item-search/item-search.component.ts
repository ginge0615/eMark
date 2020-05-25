import { Component, OnInit, Input } from '@angular/core';
import { Option } from 'src/app/models/option';
import { Item } from 'src/app/models/Item';
import { OptionsService } from 'src/app/services/options.service';
import { MessageService } from 'src/app/services/message.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-item-search',
  templateUrl: './item-search.component.html',
  styleUrls: ['./item-search.component.css']
})
export class ItemSearchComponent implements OnInit {
  @Input() selectedManufacturer: Option;
  @Input() priceFrom: number;
  @Input() priceTo: number;

  manufacturerOptionList: Option[];
  listOfColumn = [];
  listOfData: Item[];
  listCurrentData: Item[];

  compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.value === o2.value : o1 === o2);

  constructor(private optionsService: OptionsService,
    private msgService: MessageService,
    private msgPopup: NzMessageService,
    private itemService: ItemService) {
    this.msgService.hideMessage();
  }

  ngOnInit() {
    this.initOptions();
    this.initTable();
  }

  private initOptions() {
    //Init Manufactur Options
    this.optionsService.getManufacturOptions().subscribe(
      data => {
        //successful
        const respData: any = data;
        this.manufacturerOptionList = respData;
      },
      res => {
        //error
        const response: any = res;
      }
    );
  }

  private initTable() {
    this.listOfColumn = [
      {
        title: 'Item',
        compare: (a: Item, b: Item) => a.manufactur.localeCompare(b.manufactur) != 0 ? a.manufactur.localeCompare(b.manufactur) : a.itemName.localeCompare(b.itemName),
      },
      {
        title: 'Price',
        compare: (a: Item, b: Item) => a.price - b.price,
      },
      {
        title: 'Seller',
        compare: (a: Item, b: Item) => a.seller.localeCompare(b.seller),
      },
      {
        title: 'Sales volume',
        compare: (a: Item, b: Item) => a.salesVolume - b.salesVolume,
      },
      {
        title: ''
      }
    ];

    this.getAllItems();
  }

  getAllItems() {
    this.itemService.getAllItems().subscribe(
      data => {
        //successful
        const respData: any = data;
        this.listOfData = respData;
        this.listCurrentData = this.listOfData;
      },
      res => {
        //error
        const response: any = res;
        this.msgPopup.error("Failure to get items.");
      }
    );

  }

  search(context: string) {

    //If search context is not empty
    if (context && context.trim().length > 0) {
      this.itemService.search(context).subscribe(
        data => {
          //successful
          const respData: any = data;
          this.listOfData = respData;
          this.listCurrentData = this.listOfData;
        },
        res => {
          //error
          const response: any = res;
          this.msgPopup.error("Failure to search items. Please change the search context.");
        }
      );

    } else {
      //If search context is empty
      this.getAllItems();
    }

  }

  filter() {
    this.listCurrentData = [];
    isObjectData: Boolean;

    for (let data of this.listOfData) {
      let isObjectData = true;

      if (this.selectedManufacturer?.label) {
        isObjectData = data.manufactur === this.selectedManufacturer.label;
      }

      if (isObjectData && this.priceFrom) {
        isObjectData = data.price >= this.priceFrom;
      }

      if (isObjectData && this.priceTo) {
        isObjectData = data.price <= this.priceTo;
      }

      if (isObjectData) {
        this.listCurrentData.push(data);
      }
    }

  }

  clear() {
    this.selectedManufacturer = undefined;
    this.priceFrom = undefined;
    this.priceTo = undefined;
    this.listCurrentData = this.listOfData;
  }

}
