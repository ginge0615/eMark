import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public refreshCount(buyerId : string) {
    this.http.get(environment.baseUrl + "/cart/count/" + buyerId, httpOptions).subscribe(
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
    return this.http.post(environment.baseUrl + "/cart", JSON.stringify(model), httpOptions);
  }

  public getCart(buyerId : string) {
    return this.http.get(environment.baseUrl + "/cart/" + buyerId, httpOptions);
  }

  public delete(id: string) {

    return this.http.delete(environment.baseUrl + "/cart/",
      {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
        params: { 'id': id }
      })
  }
}
