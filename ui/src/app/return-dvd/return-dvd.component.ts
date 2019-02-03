import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-return-dvd',
  templateUrl: './return-dvd.component.html',
  styleUrls: ['./return-dvd.component.css']
})
export class ReturnDvdComponent implements OnInit {
  public ISBN;
  constructor(private appService: AppService) { }

  ngOnInit() {
  }
  public returnDvd(): void {
    this.appService.returnedDVD(this.ISBN).subscribe((data: any) => {
      alert(data);
    });
  }
}
