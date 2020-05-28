import { Item } from './Item';

export interface PurchaseHistory extends Item {
    purchaseNumber:number;
    datetime:Date;
    transactionAmount:number;
    itemId : number;
}

