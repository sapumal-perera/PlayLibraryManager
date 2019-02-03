import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular/main';
import { AppComponent } from './app.component';
import {
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatRippleModule
} from '@angular/material';
import {  MatChipsModule } from '@angular/material';
import { AppService } from './app.service';
import { AppHttpInterceptorService } from './http-interceptor.service';
import { AddBookComponent } from './add-book/add-book.component';
import { AddDvdComponent } from './add-dvd/add-dvd.component';
import { AppRoutingModule,routingComponent } from '../app-routing.module';
import { BorrowBookComponent } from './borrow-book/borrow-book.component';
import { BorrowDvdComponent } from './borrow-dvd/borrow-dvd.component';
import { DeleteBookComponent } from './delete-book/delete-book.component';
import { DeleteDvdComponent } from './delete-dvd/delete-dvd.component';
import { ReturnBookComponent } from './return-book/return-book.component';
import { ReturnDvdComponent } from './return-dvd/return-dvd.component';
import { ItemListComponent } from './item-list/item-list.component';
import { GenerateReportComponent } from './generate-report/generate-report.component';
import {MatTableModule,MatIconModule} from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Component, Injector } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { EditBookComponent } from './item-list/edit-book/edit-book.component';
import { OpenViewComponent } from './open-view/open-view.component';
import { MembersComponent } from './members/members.component';
const routes: Routes = [

];

@NgModule({
  declarations: [
    AppComponent,
    AddBookComponent,
    AddDvdComponent,
    routingComponent,
    BorrowBookComponent,
    BorrowDvdComponent,
    DeleteBookComponent,
    DeleteDvdComponent,
    ReturnBookComponent,
    ReturnDvdComponent,
    ItemListComponent,
    GenerateReportComponent,
    EditBookComponent,
    OpenViewComponent,
    MembersComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatTableModule,
    MatDialogModule,
    BrowserAnimationsModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    MatChipsModule,
    MatIconModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'Csrf-Token',
      headerName: 'Csrf-Token',
    }),
    AgGridModule.withComponents([]),
    RouterModule.forRoot(routes),
    AppRoutingModule
  ],
  entryComponents:[EditBookComponent],
  providers: [
    AppService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
