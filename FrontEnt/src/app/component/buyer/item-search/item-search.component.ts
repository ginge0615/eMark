import { Component, OnInit , Input} from '@angular/core';
import { Option } from 'src/app/models/option';
import { Item } from 'src/app/models/Item';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-item-search',
  templateUrl: './item-search.component.html',
  styleUrls: ['./item-search.component.css']
})
export class ItemSearchComponent implements OnInit {
  @Input() searchContent : string;
  @Input() selectedManufacturer : Option;
  @Input() priceFrom : number;
  @Input() priceTo : number;

  manufacturerOptionList : Option[];
  listOfColumn = [];
  listOfData: Item[];
  listCurrentData : Item[];

  compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.value === o2.value : o1 === o2);

  constructor(private global: GlobalService) { }

  ngOnInit() {
    this.listOfColumn = [
      {
        title: 'Item',
        compare: (a: Item, b: Item) => a.manufactur.localeCompare(b.manufactur) != 0 ? a.manufactur.localeCompare(b.manufactur): a.itemName.localeCompare(b.itemName),
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
    
    //test
    this.manufacturerOptionList  = [
      {value : "1", label : "Samsung"},
      {value : "2", label : "OPPO"},
      {value : "3", label : "XIAOMI"},
    ];
    this.listOfData = this.global.listItems;
    this.listCurrentData = this.listOfData;
  }

  search() {
    //TODO
  }

  filter() {
    this.listCurrentData = [];
    isObjectData : Boolean;

    for (let data of this.listOfData) {
      let isObjectData = true;

      if (this.selectedManufacturer?.label) {
         isObjectData = data.subcategory === this.selectedManufacturer.label;
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
