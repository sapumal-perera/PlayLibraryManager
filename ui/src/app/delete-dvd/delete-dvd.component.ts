import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-delete-dvd',
  templateUrl: './delete-dvd.component.html',
  styleUrls: ['./delete-dvd.component.css']
})
export class DeleteDvdComponent implements OnInit {
  public ISBN;
  constructor(private appService: AppService) { }

  ngOnInit() {
  }
  public deleteDvd(): void {
    this.appService.deleteDVD(this.ISBN).subscribe((data: any) => {
      alert(data.toString());
    });
  }
}
