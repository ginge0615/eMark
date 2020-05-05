import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzIconService } from 'ng-zorro-antd/icon';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service'
import { User } from '../models/user'

@Component({
  selector: 'app-login',
  templateUrl:'./login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit {
  validateForm: FormGroup;
  passwordVisible = false;

  userName : string;
  password : string;
  role : string;
  user : User;

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
      this.user  = {
        id: 0,
        name:this.userName,
        password : this.password,
        role : this.role ,
        token : ''};

      this.userService.postSignIn(this.user).subscribe(
        data => {
          console.log(JSON.stringify(data));
          const info: any = data;
          if (200 === info.code) {
              console.log('登录成功，调转详情页');
              sessionStorage.setItem('token', info.result.token);
              console.log("token=" + info.result.token);

              if (this.role === "1") {
                this.router.navigate(['/emart-buyer']);
              } else if(this.role === "2") {
                this.router.navigate(['/emart-seller']);
              }
          } else {
            console.log('登录失败，弹出MSG');

          }
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
