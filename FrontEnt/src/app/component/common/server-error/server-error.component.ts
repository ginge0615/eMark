import { Component, OnInit, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-server-error',
  templateUrl: './server-error.component.html',
  styleUrls: ['./server-error.component.css']
})
export class ServerErrorComponent implements OnInit {

  @Output() errorCode: number;
  @Output() errorMsg: string;

  constructor(private router: Router, private activedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.errorCode = this.activedRoute.snapshot.queryParams["code"];

    switch (this.errorCode) {
      case 403:
        this.errorMsg = "Sorry, you are not authorized to access this page."
        break;
      case 404:
        this.errorMsg = "Sorry, the page you visited does not exist."
        break;
      default:
          this.errorCode = 500;
          this.errorMsg = "Sorry, there is an error on server."
    }

  }

  backHome() {
    this.router.navigate(['/']);
  }

}
