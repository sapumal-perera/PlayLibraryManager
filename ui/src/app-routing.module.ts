import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AddBookComponent} from "./app/add-book/add-book.component";
import {AddDvdComponent} from "./app/add-dvd/add-dvd.component";
import {DeleteBookComponent} from "./app/delete-book/delete-book.component";
import {DeleteDvdComponent} from "./app/delete-dvd/delete-dvd.component";
import {ItemListComponent} from "./app/item-list/item-list.component";
import {BorrowBookComponent} from "./app/borrow-book/borrow-book.component";
import {BorrowDvdComponent} from "./app/borrow-dvd/borrow-dvd.component";
import {ReturnBookComponent} from "./app/return-book/return-book.component";
import {ReturnDvdComponent} from "./app/return-dvd/return-dvd.component";
import {OpenViewComponent} from "./app/open-view/open-view.component";
import {GenerateReportComponent} from "./app/generate-report/generate-report.component";
import {MembersComponent} from "./app/members/members.component";
const routes: Routes = [
  {path: 'addBook', component: AddBookComponent},
  {path: 'addDvd', component: AddDvdComponent},
  {path: 'deleteBook', component: DeleteBookComponent},
  {path: 'deleteDvd', component: DeleteDvdComponent},
  {path: 'itemList', component: ItemListComponent},
  {path: 'borrowBook', component: BorrowBookComponent},
  {path: 'borrowDvd', component: BorrowDvdComponent},
  {path: 'returnBook', component: ReturnBookComponent},
  {path: 'returnDvd', component: ReturnDvdComponent},
  {path: 'generateReport', component: GenerateReportComponent},
  {path: 'openView', component: OpenViewComponent},
  {path: 'members', component: MembersComponent}
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
export const routingComponent = [AddBookComponent,AddDvdComponent,DeleteBookComponent,DeleteDvdComponent,ItemListComponent,BorrowBookComponent,BorrowDvdComponent,ReturnBookComponent,ReturnDvdComponent,GenerateReportComponent,MembersComponent];
