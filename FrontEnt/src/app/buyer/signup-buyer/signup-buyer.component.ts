import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, } from '@angular/router';

@Component({
  selector: 'app-signup-buyer',
  templateUrl: './signup-buyer.component.html',
  styleUrls: ['./signup-buyer.component.css']
})
export class SignupBuyerComponent implements OnInit {
  validateForm: FormGroup;

  @Input() username : string;

  submitForm(): void {
    let hasError : boolean = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }

    if (!hasError) {
      this.router.navigate(['/login'], { queryParams: { userName: this.username } });
    }

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

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      mobile: [null, [Validators.required]],
    });
  }
}
