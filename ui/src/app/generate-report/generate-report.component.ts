import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-generate-report',
  templateUrl: './generate-report.component.html',
  styleUrls: ['./generate-report.component.css']
})
export class GenerateReportComponent implements OnInit {
  overDueDetails: Object[];
  constructor(private appService: AppService) {
    this.overDueDetails = [];

  }

  ngOnInit() {
    this.getReport();
  }
  public getReport(): void {
    this.appService.getReport().subscribe((data: any) => {
      // this.postResponse = data;
      this.overDueDetails = data;

    });
  }
  displayedColumns = ['isbn','type', 'totalFee', 'borrowerId'];
}
