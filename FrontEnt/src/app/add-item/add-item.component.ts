import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { OptionInterface } from '../option-interface';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  validateForm: FormGroup;

  optionList : OptionInterface[] = [
    {value : "1", label : "Electronic"},
    {value : "2", label : "Dress"},
    {value : "3", label : "Book"},
  ];

  selectcategory : OptionInterface;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      
    });
  }

}
