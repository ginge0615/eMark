
import {Option} from 'src/app/models/option';
export interface Item {
  id : string;
  category : string;
  subcategory : string;
  manufactur : string;
  item: string;
  price: number;
  tax : number;
  pictures: string[];
  descriptions:Option[];
  seller: string;
  stock : number;
  volume : number;
  datetime : Date;
  purchaseNum : number;
}
