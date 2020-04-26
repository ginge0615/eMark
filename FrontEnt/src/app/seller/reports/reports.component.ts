import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { en_US, NzI18nService, zh_CN } from 'ng-zorro-antd/i18n';
import { Item } from 'src/app/common/interface/Item';
import { GlobalService } from 'src/app/common/service/global.service';

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

  listOfData: Item[];

  constructor(private fb: FormBuilder, private i18n: NzI18nService, private global: GlobalService) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      item: [null],
      sellDate: [null],
    });

    this.i18n.setLocale(en_US);
  }
}