import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-borrow-book',
  templateUrl: './borrow-book.component.html',
  styleUrls: ['./borrow-book.component.css']
})
export class BorrowBookComponent implements OnInit {
  public ISBN;
  public BORROWER
  public RETURNDAYS;
  public reserve = false;
  public reserveDetails =[];
  public  viewReserveTable = false;
  constructor(private appService: AppService) { }

  ngOnInit() {
  }
  public borrowBook(): void {
    this.appService.borrowBook(this.ISBN,this.RETURNDAYS,this.BORROWER).subscribe((data: any) => {
      alert(data.toString());
      if(data.toString() === "This Book is Already Borrowed."){
        this.reserve=true;
      }
    });
  }
  public reserveBook(): void {
    this.appService.reserveBook(this.ISBN,this.RETURNDAYS ,this.BORROWER).subscribe((data: any) => {
      alert(data.toString());
    });
  }

  public deleteEntry(id): void {
    this.appService.deleteEntry(id).subscribe((data: any) => {
      alert(data.toString());
      this.reserveTable();
    });
  }
  public reserveTableHide():void{
    this.viewReserveTable = false;
  }
  public reserveTable(): void {
    this.viewReserveTable = true;
    this.appService.reserveTableData().subscribe((data: any) => {
      this.reserveDetails = data;
    });
  }
  displayedColumns = ['isbn', 'borrower','totalDays', 'remainingDays', 'action'];
}
