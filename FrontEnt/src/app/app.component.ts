import {Component, OnInit} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  //templateUrl: './app.component.html',
  templateUrl: './login/login.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'eMart';

  constructor() {
  }

  ngOnInit() {
  }
}