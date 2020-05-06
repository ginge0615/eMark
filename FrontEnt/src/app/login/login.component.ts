import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzIconService } from 'ng-zorro-antd/icon';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service'
import { User } from '../models/user'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;
  passwordVisible = false;

  userName: string;
  password: string;
  role: string;
  showError : boolean = false;

  submitForm(): void {
    console.info("uername=" + this.userName);
    console.info("password=" + this.password);

    let hasError: boolean = false;
    this.showError = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();

      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }

    if (!hasError) {
      let user = {
        name: this.userName,
        password: this.password,
        role: this.role
      };

      this.userService.postSignIn(user).subscribe(
        data => {
          const respData: any = data;

          if (respData.token) {
            //login sucessful
            sessionStorage.setItem('token', respData.token);

            if (this.role === "1") {
              this.router.navigate(['/emart-buyer']);
            } else if (this.role === "2") {
              this.router.navigate(['/emart-seller']);
            }
          } else {
            //login failure
            this.showError = true;
          }
          
        },
        res => {
          const response: any = res;
          console.log(response.status);
        }
      );
    }
  }

  constructor(private fb: FormBuilder,
    private iconService: NzIconService,
    private router: Router,
    private routeInfo: ActivatedRoute,
    private userService: UserService) {

    this.iconService.fetchFromIconfont({
      scriptUrl: 'https://at.alicdn.com/t/font_8d5l8fzk5b87iudi.js'
    });

  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      role: [null, [Validators.required]]
    });

    this.userName = this.routeInfo.snapshot.queryParams["userName"];
    this.role = "1"; //As Buyer
  }
}
