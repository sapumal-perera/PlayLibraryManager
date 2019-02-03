import {Component, OnInit} from '@angular/core';

import { AppService } from './app.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string;

  ngOnInit() {
    this.router.navigate(['openView']);
  }
  constructor(private appService: AppService, private router: Router,) {
  }


}
