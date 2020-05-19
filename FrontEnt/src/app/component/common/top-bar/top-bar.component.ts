import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

  constructor(private router: Router, public global: GlobalService, private authService : AuthService) {
  }

  logout() {
    window.sessionStorage.removeItem("token");
    window.sessionStorage.removeItem("role");
    this.authService.logout
    this.router.navigate(['/']);
  }

  ngOnInit() {
  }
}
