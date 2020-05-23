import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Option } from 'src/app/models/option';
import { OptionsService } from 'src/app/services/options.service'

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  labelSize = 6;
  controlSize = 14;

  validateForm: FormGroup;

  categoryOptionList: Option[] = [];

  selectedCategory: Option;

  subCategoryOptionList: Option[] = [];

  selectedSubCategory: Option;

  manufacturerOptionList: Option[] = [];

  selectedManufacturer: Option;

  itemNameValue: string;
  priceValue: number = 1;
  stockValue: number = 1;

  compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.value === o2.value : o1 === o2);
  formatterDollar = (value: number) => `$ ${value}`;
  parserDollar = (value: string) => value.replace('$ ', '');
  formatterPercent = (value: number) => `${value} %`;
  parserPercent = (value: string) => value.replace(' %', '');

  constructor(private fb: FormBuilder, private optionsService: OptionsService) { }

  ngOnInit() {
    this.validateForm = this.fb.group({
      category: new FormControl(null, [Validators.required]),
      subCategory: new FormControl(null, [Validators.required]),
      manufacturer: new FormControl(null, [Validators.required]),
      itemName: new FormControl(null, [Validators.required]),
      price: new FormControl(null, [Validators.required]),
      stock: new FormControl(null, [Validators.required]),
    });

    this.initOptions();

    this.addField();
  }

  private initOptions() {
    //Init Category Options
    this.optionsService.getCategoryOptions().subscribe(
      data => {
        //successful
        const respData: any = data;
        this.categoryOptionList = respData;
      },
      res => {
        //error
        const response: any = res;
      }
    );

    //Init Manufactur Options
    this.optionsService.getManufacturOptions().subscribe(
      data => {
        //successful
        const respData: any = data;
        this.manufacturerOptionList = respData;
      },
      res => {
        //error
        const response: any = res;
      }
    );
  }

  changeCategory(selectedOption: Option): void {
    if (selectedOption) {
      console.info("selected category=" + selectedOption.value);

      this.optionsService.getSubCategoryOptions(selectedOption.value).subscribe(
        data => {
          //successful
          const respData: any = data;
          this.subCategoryOptionList = respData;
        },
        res => {
          //error
          const response: any = res;
        }
      );
    }
  }


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
}
