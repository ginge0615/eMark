import { Component, OnInit , Input } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SellerModel } from 'src/app/models/SellerModel';
import { UserService } from 'src/app/services/user.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-signup-seller',
  templateUrl: './signup-seller.component.html',
  styleUrls: ['./signup-seller.component.css']
})
export class SignupSellerComponent implements OnInit {
  validateForm: FormGroup;

  @Input() model : SellerModel = new SellerModel();
  
  constructor(private fb: FormBuilder, 
    private router: Router, 
    private userService: UserService,
    private msgService : MessageService) {

      this.msgService.hideMessage();

    }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      company: [null, [Validators.required]],
      contact: [null, [Validators.required]],
      gstin: [null, [Validators.required]],
      postal: [null, [Validators.required]],
      website: [null, null],
      brief: [null, null],
    });
  }

  submitForm(): void {
    let hasError : boolean = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }

    if (hasError) return;

    this.msgService.hideMessage();

    this.userService.signinAsSeller(this.model).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.router.navigate(['/singup-success']);
      },
      res => {
        //error
        const response: any = res;

        if (response.status === 406) {
          this.msgService.showErrorMsg(response.error);
        } else {
          this.router.navigate(['/server-error',response.status]);
        }
      }
    );
  }

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.checkPassword.updateValueAndValidity());
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };
}
