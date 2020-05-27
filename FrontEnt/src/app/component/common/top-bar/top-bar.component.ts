import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import {CartService} from 'src/app/services/cart.service'

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

  constructor(private router: Router, 
    public cartService: CartService, 
    private authService : AuthService,
    public globalService: GlobalService) {
  }

  logout() {
    window.sessionStorage.removeItem("token");
    window.sessionStorage.removeItem("userId");
    window.sessionStorage.removeItem("role");
    this.authService.logout
    this.router.navigate(['/']);
  }

  getItemsCountInCart() : number {
    if (window.sessionStorage.getItem("count")) {
      return Number(window.sessionStorage.getItem("count"));
    }

    return 0;
  }

  ngOnInit() {
  }
}
