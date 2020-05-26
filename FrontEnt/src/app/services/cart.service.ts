import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Item } from 'src/app/models/Item'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public addToCart(model) {
    return this.http.post("/remote/cart", JSON.stringify(model), httpOptions);
  }

  public getCart(buyerId: string) {
    return this.http.get("/remote/cart/" + buyerId, httpOptions);
  }

  public delete(id: string) {

    return this.http.delete("/remote/cart/",
      {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
        params: { 'id': id }
      })
  }
}
