import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { OptionInterface } from '../option-interface';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  labelSize = 5;
  controlSize = 10;

  validateForm: FormGroup;

  categoryOptionList : OptionInterface[] = [
    {value : "1", label : "Electronic"},
    {value : "2", label : "Dress"},
    {value : "3", label : "Book"},
  ];

  selectedCategory : OptionInterface ;

  subCategoryOptionList : OptionInterface[] = [
    {value : "1", label : "Mobile"},
    {value : "2", label : "TV"},
    {value : "3", label : "MP4"},
  ];

  selectedSubCategory : OptionInterface ;

  manufacturerOptionList : OptionInterface[] = [
    {value : "1", label : "Samsung"},
    {value : "2", label : "OPPO"},
    {value : "3", label : "XIAOMI"},
  ];

  selectedManufacturer : OptionInterface ;

  itemNameValue : string;
  priceValue : number = 1;
  stockValue : number = 1;
  taxValue : number = 0;

  compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.value === o2.value : o1 === o2);
  formatterDollar = (value: number) => `$ ${value}`;
  parserDollar = (value: string) => value.replace('$ ', '');
  formatterPercent = (value: number) => `${value} %`;
  parserPercent = (value: string) => value.replace(' %', '');

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      category: new FormControl(null, [Validators.required]),
      subCategory: new FormControl(null, [Validators.required]),
      manufacturer: new FormControl(null, [Validators.required]),
      itemName: new FormControl(null, [Validators.required]),
      price: new FormControl(null, [Validators.required]),
      stock: new FormControl(null, [Validators.required]),
      tax: new FormControl(null, [Validators.required]),
    });
  }

}
