
import {Option} from 'src/app/models/option';
export interface Item {
  id : string;
  cover: string;
  category : string;
  subcategory : string;
  manufactur : string;
  itemName: string;
  price: number;
  seller: string;
  salesVolume : number;
  stock : number;
  tax:number;
}
