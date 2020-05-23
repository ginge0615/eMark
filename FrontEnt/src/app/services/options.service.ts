import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OptionsService {

  constructor(private http: HttpClient) { }

  public getCategoryOptions() {
    return this.http.get("/category", httpOptions);
  }

  public getSubCategoryOptions(categoryId) {
    return this.http.get("/category/" + categoryId, httpOptions);
  }

  public getManufacturOptions() {
    return this.http.get("/manufactur", httpOptions);
  }

}
