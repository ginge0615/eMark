import { Component, OnInit } from '@angular/core';
import { en_US, NzI18nService, zh_CN } from 'ng-zorro-antd/i18n';
import { ReportModel } from 'src/app/models/ReportModel';
import { GlobalService } from 'src/app/services/global.service';
import { ReportService } from 'src/app/services/report.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import differenceInCalendarDays from 'date-fns/differenceInCalendarDays';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';
import format from 'date-fns/format';
import { JSON2SheetOpts } from 'xlsx';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  validateForm: FormGroup;
  item : string;
  sellDate : Date[];
  listOfData: ReportModel[];
  today = new Date();
  baseUrl : string = environment.baseUrl;

  disabledDate = (current: Date): boolean => {
    // Can not select days before today and today
    return differenceInCalendarDays(current, this.today) > 0;
  };

  constructor(private fb: FormBuilder,private i18n: NzI18nService, private globalService: GlobalService, private reportService: ReportService, private router : Router) { }

  ngOnInit(): void {
    this.i18n.setLocale(en_US);
    this.validateForm = this.fb.group({
      item: [null],
      sellDate: [null],
    });
  }

  search(): void {
    this.reportService.searchReports(
      this.globalService.getUserId(),
      this.item,
      this.sellDate
      ).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.listOfData = respData;
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error',response.status]);
      }
    );
  }

  sellDateChange() {
    console.info("sellDate=" + this.sellDate);
  }

  exportTable() {
    let exportItem : any[] = [];
    
    let header = ["Item","Price","Sales volume","Income"];
    exportItem.push(header);

    for (let row of this.listOfData) {
      exportItem.push([row.itemName, row.price, row.salesVolume , row.transactionAmount]);
    }
  
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportItem, {skipHeader : true});
    const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    this.saveAsExcelFile(excelBuffer, 'report');
}

private saveAsExcelFile(buffer: any, fileName: string) {
  const data: Blob = new Blob([buffer], {
    type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8'
  });
  FileSaver.saveAs(data, fileName + '_' + format(this.today, 'yyyyMMddHHmmss') + '.xlsx');
}
}