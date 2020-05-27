import { Item } from './Item';

export interface CartModel extends Item {
    buyerId : number;
    sellerId : number;
    itemId : number;
    number : number;
}
