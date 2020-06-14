import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

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

  public getBaseUrl() : string {
    return environment.baseUrl;
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
 
}
