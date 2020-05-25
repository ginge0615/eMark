import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  public addItem(item) {
    return this.http.post("/remote/item", JSON.stringify(item), httpOptions);
  }

  public getAllItemsBySeller(sellerId : string) {
    return this.http.get("/remote/item/all/" + sellerId, httpOptions);
  }

  public search(context:string) {
    return this.http.get("/remote/item/search/" + context, httpOptions);
  }

  public viewDetail(itemId:string) {
    return this.http.get("/remote/item/detail/" + itemId, httpOptions);
  }

  public updateStock(itemId:string, stock : number) {
    let model = {
      id : itemId,
      stock : stock
    }
    return this.http.put("/remote/item/stock"  ,JSON.stringify(model),  httpOptions);
  }

  public getAllItems() {
    return this.http.get("/remote/item", httpOptions);
  }

}
