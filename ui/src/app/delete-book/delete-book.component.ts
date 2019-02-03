import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
@Component({
  selector: 'app-delete-book',
  templateUrl: './delete-book.component.html',
  styleUrls: ['./delete-book.component.css']
})
export class DeleteBookComponent implements OnInit {
  public ISBN;
  constructor(private appService: AppService) { }

  ngOnInit() {
  }
  public deleteBook(): void {
    this.appService.deleteBook(this.ISBN).subscribe((data: any) => {
      alert(data.toString());
    });
  }
}
