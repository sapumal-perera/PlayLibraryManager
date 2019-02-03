import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})
export class ReturnBookComponent implements OnInit {
  public ISBN;
  constructor(private appService: AppService) { }

  ngOnInit() {
  }
  public returnBook(): void {
    this.appService.returnedBook(this.ISBN).subscribe((data: any) => {
      alert(data);
    });
  }
}
