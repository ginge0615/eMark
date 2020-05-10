import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {User} from '../models/user';

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
    return this.http.post(`/user/login`, JSON.stringify(user), httpOptions);
  }

  

}
