import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {MatChipInputEvent} from "@angular/material";
import {COMMA, ENTER} from '@angular/cdk/keycodes';
export interface Authors {
  name: string;
}
@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})

export class AddBookComponent implements OnInit {

  public ISBN;
  public TITLE;
  public AUTHOR;
  public PUBLISHER;
  public PAGECOUNT;
  title: string;
  postRequestResponse: string;
  postResponse: string;
  public PARALLEL_REQ_COUNT;

  constructor(private appService: AppService) {
  }

  ngOnInit() {
  }

  public postData(): void {
    this.appService.sendData().subscribe((data: any) => {
      this.postRequestResponse = data;
    });
  }

  public addBook(): void {
    this.AUTHOR = "";
    for (let i = 0; i <this.authors.length ; i++) {
      this.AUTHOR += this.authors[i].name+",";

    }
    this.appService.addBook(this.ISBN,this.TITLE,this.AUTHOR,this.PUBLISHER,this.PAGECOUNT).subscribe((data: any) => {
      alert(data);
    });
  }

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  authors: Authors[] = [
  ];

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.authors.push({name: value.trim()});
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(author: Authors): void {
    const index = this.authors.indexOf(author);

    if (index >= 0) {
      this.authors.splice(index, 1);
    }
  }
}
