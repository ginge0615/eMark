import { Injectable } from '@angular/core';
import { Item } from '../models/Item';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {
  constructor() { }

  public isLogined() : boolean {
    if (window.sessionStorage["token"]) {
      return true;
    }

    return false;
  }

  public isBuyer() : boolean {
    return window.sessionStorage["role"] === "1";
  }

  public isSeller() : boolean {
    return window.sessionStorage["role"] === "2";
  }

  public getUserId() : string {
    return window.sessionStorage.getItem("userId");
  }
 
  //test
  public listItems : Item[] = [];

  public cartItems : Item[] = [];

}
