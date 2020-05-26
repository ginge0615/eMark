import { Item } from './Item';

export interface ItemDetail extends Item{
    pictures:string[];
    number:number;
    descriptions:string[];
    sellerId:number;
}
