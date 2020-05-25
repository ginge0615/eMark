import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public get currentUserToken(): string {
    return sessionStorage.getItem('token');
  }

  public login(user) {
    return this.http.post("/remote/user/login", JSON.stringify(user), httpOptions);
  }

  public signinAsBuyer(buyer) {
    return this.http.post("/remote/user/signinasbuyer", JSON.stringify(buyer), httpOptions);
  }

  public signinAsSeller(seller) {
    return this.http.post("/remote/user/signinasseller", JSON.stringify(seller), httpOptions);
  }

  

}
