import { Item } from './Item';

export interface ItemDetail extends Item{
    pictures:any;
    number:number;
    descriptions:string[];
    sellerId:number;
}
