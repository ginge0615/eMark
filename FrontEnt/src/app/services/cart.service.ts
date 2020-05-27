import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public refreshCount(buyerId : string) {
    this.http.get("/remote/cart/count/" + buyerId, httpOptions).subscribe(
      data => {
        //successful
        const respData: any = data;
        window.sessionStorage.setItem("count", respData);
      },
      res => {
        //error
        const response: any = res;
        window.sessionStorage.setItem("count", "0");
      }
    );
  }
  
  public addToCart(model) {
    return this.http.post("/remote/cart", JSON.stringify(model), httpOptions);
  }

  public getCart(buyerId : string) {
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
