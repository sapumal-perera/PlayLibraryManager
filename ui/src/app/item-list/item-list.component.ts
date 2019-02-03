import { Component, OnInit, Input, Output, OnChanges, EventEmitter  } from '@angular/core';
import { AppService } from '../app.service';
import {MatButtonModule, MatCheckboxModule} from '@angular/material';
import { MatDialogModule,MatDialogRef , MatDialog} from '@angular/material/dialog';
import {EditBookComponent} from "./edit-book/edit-book.component";
import {GridOptions} from "ag-grid-community";
@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  bookDetails: Object[];
  dvdDetails: Object[];
  public gridOptions:GridOptions;
  public BOOKTITLE;
  public itemTitle;
  public itemIsbn;
  public rowData:any[];
  public rowDataDvd:any[];
  public columnDefs:any[];
  public columnDefsDvd:any[];
  public itemAvalablty;
  public isSearchBook: boolean;
  public isSearchDvd:boolean;
  public itemType;
  constructor(private appService: AppService,public dialog: MatDialog) {
    this.gridOptions = <GridOptions>{
      onGridReady: () => {
        this.gridOptions.api.sizeColumnsToFit();
      }
    };
    this.columnDefs = [
      {headerName: "ISBN", field: "isbn"},
      {headerName: "Title", field: "title"},
      {headerName: "Authors", field: "authors"},
      {headerName: "TotalNumberOfPages", field: "totalNumberOfPages"},
      {headerName: "Publisher", field: "publisher"},
      {headerName: "Sector", field: "sector"},
    ];//['dvdId', 'title','producer','actors','availableLanguages','availableSubtitles','sector','action'];
    this.columnDefsDvd = [
      {headerName: "Id", field: "dvdId"},
      {headerName: "Title", field: "title"},
      {headerName: "Producer", field: "producer"},
      {headerName: "Actors", field: "actors"},
      {headerName: "Languages", field: "availableLanguages"},
      {headerName: "Sector", field: "sector"},
    ];
    this.bookDetails = [];
    this.dvdDetails = [];
  }
  openDialog() {
    this.dialog.open(EditBookComponent, {

    });
  }

  ngOnInit() {
this.getBook();
  }
  public getBook(): void {
    this.appService.getBooks().subscribe((data: any) => {
     // this.postResponse = data;
      this.bookDetails = data;
      this.rowData = data;

    });
    this.appService.getDvds().subscribe((data: any) => {
      // this.postResponse = data;
      this.dvdDetails = data;
      this.rowDataDvd = data;

    });
  }
public searchBook(): void{
    this.isSearchBook = this.isSearchBook ? false : true;
}
  public searchDvd(): void{
    this.isSearchDvd = this.isSearchDvd ? false : true;
  }
  public getItem(): void {
    this.appService.findBook(this.BOOKTITLE).subscribe((data: any) => {
      // this.postResponse = data;
    if(data != null){
  this.itemAvalablty = "Availability: "+ data.sector;
  this.itemIsbn = "ISBN: "+data.isbn;
  this.itemTitle= "Title: "+data.title;
  this.itemType = "Type: Book";

}

    });
    this.appService.findDvd(this.BOOKTITLE).subscribe((data: any) => {
      // this.postResponse = data;

      if(data != null){
        this.itemAvalablty = "Availability: "+ data.sector;
        this.itemIsbn = "ID: "+data.dvdId;
        this.itemTitle= "Title: "+data.title;
        this.itemType = "Type: Dvd";
      }
    });
  }
  public editDvd(isbn): void {
    this.appService.getBooks().subscribe((data: any) => {
      // this.postResponse = data;
      this.bookDetails = data;

    });
    this.appService.getDvds().subscribe((data: any) => {
      // this.postResponse = data;
      this.dvdDetails = data;

    });
  }

  public deleteDvd(id): void {
    this.appService.deleteDVD(id).subscribe((data: any) => {
      alert(data.toString());
      this.ngOnInit();
    });
  }
  public deleteBook(isbn): void {
    this.appService.deleteBook(isbn).subscribe((data: any) => {
      alert(data.toString());
      this.ngOnInit();
    });
  }
  displayedColumns = ['isbn', 'title','authors', 'totalNumberOfPages','publisher','sector','action'];
  dataSource = this.bookDetails;

  displayedSecondColumns = ['dvdId', 'title','producer','actors','availableLanguages','availableSubtitles','sector','action'];
  secondDataSource = this.dvdDetails;

}
