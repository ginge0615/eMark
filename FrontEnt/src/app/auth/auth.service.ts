import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _isLoggedIn = false;

  // store the URL so we can redirect after logging in
  redirectUrl: string;
  isLoggedIn() {
    if (this._isLoggedIn) return true;

    if (window.sessionStorage["USER_NAME"]) {
      this._isLoggedIn = true;
      return true;
    }

    return false;
  }

  logout() {
    this._isLoggedIn = false;
  }
}
