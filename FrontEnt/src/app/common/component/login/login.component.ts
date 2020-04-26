import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzIconService } from 'ng-zorro-antd/icon';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl:'./login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit {
  validateForm: FormGroup;
  passwordVisible = false;
  password: string;

  userName : string;
  userType : string = "1"; //1:Buyer  2:Seller

  submitForm(): void {
    console.info("uername=" + this.userName);
    console.info("password=" + this.password);

    let hasError : boolean = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();

      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }

    if (!hasError) {
      window.localStorage["USER_NAME"] = this.userName;
      window.localStorage["USER_TYPE"] = this.userType;
      
      //TODO
      if (this.userType === "1") {
        this.router.navigate(['/emart-buyer']);
      } else if(this.userType === "2") {
        this.router.navigate(['/emart-seller']);
      }
    }
  }

  constructor(private fb: FormBuilder, private iconService: NzIconService, private router: Router, private routeInfo: ActivatedRoute) {
    this.iconService.fetchFromIconfont({
      scriptUrl: 'https://at.alicdn.com/t/font_8d5l8fzk5b87iudi.js'
    });

  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      userType: [null, [Validators.required]]
    });

    this.userName = this.routeInfo.snapshot.queryParams["userName"];
    console.info("LoginUserName=" + this.userName);
  }
}
