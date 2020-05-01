import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/common/global.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  constructor(private router: Router, public global: GlobalService, private authService : AuthService) {
  }

  logout() {
    window.sessionStorage.removeItem("USER_NAME");
    window.sessionStorage.removeItem["USER_TYPE"];
    this.authService.logout
    this.router.navigate(['/']);
  }

  ngOnInit() {
  }

}
