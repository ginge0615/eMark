import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/common/service/global.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  constructor(private router: Router, public global: GlobalService) {
  }

  logout() {
    window.localStorage.removeItem("USER_NAME");
    window.localStorage.removeItem["USER_TYPE"];
    this.router.navigate(['/']);
  }

  ngOnInit() {
  }

}
