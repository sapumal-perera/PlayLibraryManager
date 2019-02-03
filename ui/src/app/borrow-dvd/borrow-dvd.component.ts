import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-borrow-dvd',
  templateUrl: './borrow-dvd.component.html',
  styleUrls: ['./borrow-dvd.component.css']
})
export class BorrowDvdComponent implements OnInit {
  public ISBN;
  public BORROWER;
  public RETURNDAYS;

  constructor(private appService: AppService) { }

  ngOnInit() {
  }
  public borrowDvd(): void {
    this.appService.borrowDVD(this.ISBN,this.RETURNDAYS ,this.BORROWER).subscribe((data: any) => {
      alert(data.toString());
    });
  }

}
