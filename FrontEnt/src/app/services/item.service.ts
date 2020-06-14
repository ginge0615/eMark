import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  public addItem(item) {
    return this.http.post(environment.baseUrl + "/item", JSON.stringify(item), httpOptions);
  }

  public getAllItemsBySeller(sellerId : string) {
    return this.http.get(environment.baseUrl +"/item/all/" + sellerId, httpOptions);
  }

  public search(context:string) {
    return this.http.get(environment.baseUrl +"/item/search/" + context, httpOptions);
  }

  public viewDetail(itemId:string) {
    return this.http.get(environment.baseUrl +"/item/detail/" + itemId, httpOptions);
  }

  public updateStock(itemId:string, stock : number) {
    let model = {
      id : itemId,
      stock : stock
    }
    return this.http.put(environment.baseUrl +"/item/stock"  ,JSON.stringify(model),  httpOptions);
  }

  public getAllItems() {
    return this.http.get(environment.baseUrl +"/item", httpOptions);
  }

}
