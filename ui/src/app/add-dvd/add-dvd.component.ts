import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {MatChipInputEvent} from "@angular/material";
import {Authors} from "../add-book/add-book.component";
export interface Actors {
  name: string;
}
export interface Languages {
  name: string;
}
export interface Subtitles {
  name: string;
}
@Component({
  selector: 'app-add-dvd',
  templateUrl: './add-dvd.component.html',
  styleUrls: ['./add-dvd.component.css']
})
export class AddDvdComponent implements OnInit {

  public DvdID;
  public TITLE;
  public PRODUCER;
  public PUBLISHER;
  public ACTOR;
  public LANG;
  public SUBS;
  constructor(private appService: AppService) { }

  ngOnInit() {
  }
  public addDVD(): void {
    this.ACTOR = "";
    this.LANG = "";
    this.SUBS ="";
    for (let i = 0; i <this.actors.length ; i++) {
      this.ACTOR += this.actors[i].name+",";

    }
    for (let i = 0; i <this.langs.length ; i++) {
      this.LANG += this.langs[i].name+",";

    }
    for (let i = 0; i <this.subs.length ; i++) {
      this.SUBS += this.subs[i].name+",";

    }
    this.appService.addDVD(this.DvdID,this.TITLE,this.PRODUCER,this.PUBLISHER,this.ACTOR,this.LANG,this.SUBS).subscribe((data: any) => {
      alert(data);
    });
  }
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  actors: Actors[] = [
  ];
  langs: Languages[] = [
  ];
  subs: Subtitles[] = [
  ];

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.actors.push({name: value.trim()});
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(author: Authors): void {
    const index = this.actors.indexOf(author);

    if (index >= 0) {
      this.actors.splice(index, 1);
    }
  }
  addLang(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.langs.push({name: value.trim()});
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  removeLang(author: Authors): void {
    const index = this.langs.indexOf(author);

    if (index >= 0) {
      this.langs.splice(index, 1);
    }
  }
  addSubs(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.subs.push({name: value.trim()});
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  removeSubs(sub: Subtitles): void {
    const index = this.subs.indexOf(sub);

    if (index >= 0) {
      this.subs.splice(index, 1);
    }
  }
}
