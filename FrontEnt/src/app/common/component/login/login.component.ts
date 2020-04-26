import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzIconService } from 'ng-zorro-antd/icon';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { GlobalService } from 'src/app/common/service/global.service';

@Component({
  selector: 'app-login',
  templateUrl:'./login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit {
  validateForm: FormGroup;
  passwordVisible = false;
  password: string;

  @Input() userName : string;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  login() : void {
    console.info("uername=" + this.userName);
    console.info("password=" + this.password);
    
    this.global.isUserAuth = true;

    //test
    this.router.navigate(['/emart']);
    
  }

  constructor(private fb: FormBuilder, private iconService: NzIconService, private router: Router, private global: GlobalService) {
    this.iconService.fetchFromIconfont({
      scriptUrl: 'https://at.alicdn.com/t/font_8d5l8fzk5b87iudi.js'
    });

  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });
  }
}
