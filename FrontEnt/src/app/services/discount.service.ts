import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/text' })
};

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  constructor(private http: HttpClient) { }

  public getDiscount(code : string) {
    return this.http.get(environment.baseUrl + "/discount/" + code, httpOptions);
  }

}
