import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Option } from 'src/app/common/option';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  labelSize = 6;
  controlSize = 14;

  validateForm: FormGroup;

  categoryOptionList : Option[] = [
    {value : "1", label : "Electronic"},
    {value : "2", label : "Dress"},
    {value : "3", label : "Book"},
  ];

  selectedCategory : Option ;

  subCategoryOptionList : Option[] = [
    {value : "1", label : "Mobile"},
    {value : "2", label : "TV"},
    {value : "3", label : "MP4"},
  ];

  selectedSubCategory : Option ;

  manufacturerOptionList : Option[] = [
    {value : "1", label : "Samsung"},
    {value : "2", label : "OPPO"},
    {value : "3", label : "XIAOMI"},
  ];

  selectedManufacturer : Option ;

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

  listOfControl: Array<{ id: number; controlInstance: string }> = [];

  addField(e?: MouseEvent): void {
    if (e) {
      e.preventDefault();
    }
    const id = this.listOfControl.length > 0 ? this.listOfControl[this.listOfControl.length - 1].id + 1 : 0;

    const control = {
      id,
      controlInstance: `description${id}`
    };
    const index = this.listOfControl.push(control);
    console.log(this.listOfControl[this.listOfControl.length - 1]);
    this.validateForm.addControl(this.listOfControl[index - 1].controlInstance, new FormControl(null, Validators.required));
  }

  removeField(i: { id: number; controlInstance: string }, e: MouseEvent): void {
    e.preventDefault();
    if (this.listOfControl.length > 1) {
      const index = this.listOfControl.indexOf(i);
      this.listOfControl.splice(index, 1);
      console.log(this.listOfControl);
      this.validateForm.removeControl(i.controlInstance);
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

    this.addField();
  }

}
