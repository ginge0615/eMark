import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { en_US, NzI18nService, zh_CN } from 'ng-zorro-antd/i18n';

interface ItemData {
  id: number;
  picture: string;
  subcategory : string;
  item: string;
  price: number;
  soldNum: number;
}

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  validateForm: FormGroup;


  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  listOfData: ItemData[] = [
    {
      id : 1,
      picture : '../../assets/pictures/samsung1.jpg',
      subcategory : 'Samsung',
      item : 'Galaxy s7',
      price: 123456,
      soldNum : 100
       },
    {
      id : 2,
      picture : '../../assets/pictures/oppo1.jpg',
      subcategory : 'OPPO',
      item : 'A5S',
      price: 8838,
      soldNum : 200,
    }

  ];

  constructor(private fb: FormBuilder, private i18n: NzI18nService) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      item: [null],
      sellDate: [null],
    });

    this.i18n.setLocale(en_US);
  }
}