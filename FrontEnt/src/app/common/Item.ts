export interface Item {
  id : string;
  category : string;
  subcategory : string;
  manufactur : string;
  item: string;
  price: number;
  tax : number;
  pictures: string[];
  descriptions:string[];
  seller: string;
  stock : number;
  volume : number;
  datetime : Date;
  purchaseNum : number;
}
