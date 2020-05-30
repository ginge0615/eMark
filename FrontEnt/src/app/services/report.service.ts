import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import format from 'date-fns/format';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  public searchReports(sellId: string, itemName: string, sellDate : Date[]) {
    let fromDate : string = "";
    let toDate : string = "";

    if (sellDate && sellDate.length >= 1) {
      fromDate = format(sellDate[0], 'yyyy-MM-dd');

      if (sellDate.length >= 2) {
        toDate = format(sellDate[1], 'yyyy-MM-dd');
      }
    }


    return this.http.get("/remote/report/",
      {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
        params: {
          'sellId': sellId,
          'item': itemName == undefined ? "" : itemName,
          'fromDate': fromDate,
          'toDate': toDate
        }
      })
  }

}
