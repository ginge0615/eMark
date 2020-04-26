import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {
  constructor() { }

  public isLogined() : boolean {
    if (window.localStorage["USER_NAME"]) {
      return true;
    }

    return false;
  }

  public isBuyer() : boolean {
    return window.localStorage["USER_TYPE"] === "1";
  }

  public isSeller() : boolean {
    return window.localStorage["USER_TYPE"] === "2";
  }
}
