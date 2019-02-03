import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/index';

/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {
  private bookAdd = '/library/addBook';
  private dvdAdd = '/library/addDVD';
  private bookDelete = '/library/deleteBook';
  private dvdDelete = '/library/deleteDVD';
  private borrowUrl = '/library/borrowBk';
  private reserveBookUrl = '/library/book/reserve';
  private reserveDetails = '/library/reserve/all';
  private reserveDelete = '/library/reserve/delete';
  private borrowDVDUrl = '/library/borrowDVD';
  private returnBook = '/library/returnBook';
  private returnDvd = '/library/returnDVD';
  private getAllBooks = '/library/getBooks';
  private getAllDvds = '/library/getDvds';
  private overdueReport = '/library/report';
  private searchBook = '/library/book';
  private searchDvd = '/library/dvd';
  private addNewMember = '/library/member/new';
  private getAllMembers = '/library/members';
  private memDelete = '/library/deleteMember';
  constructor(private http: HttpClient) {
  }

  /**
   * Makes a http get request to retrieve the welcome message from the backend service.
   */


  /**
   * Makes a http post request to send some data to backend & get response.
   */
  public sendData(): Observable<any> {
    return this.http.post(this.bookAdd, {});
  }

  public addBook(isbn:number,title:String,author:String,publisher:String,pageCount:number): Observable<any> {
    return this.http.post(this.bookAdd,{isbn:isbn,title:title,author:author,publisher:publisher,pageCount:pageCount});
  }

  public addDVD(dvdId:number,titles:String,producer:String,publishers:String,actors:String,languages:String,subtitles:String): Observable<any> {
    return this.http.post(this.dvdAdd,{dvdId:dvdId,title:titles,producer:producer,publisher:publishers,actors:actors,languages:languages,subtitles:subtitles});
  }

  public deleteBook(isbn:number): Observable<any> {
    return this.http.post(this.bookDelete,{isbn:isbn});
  }
  public deleteEntry(id:number): Observable<any> {
    return this.http.post(this.reserveDelete,{id:id});
  }
  public deleteDVD(isbn:number): Observable<any> {
    return this.http.post(this.dvdDelete,{isbn:isbn});
  }
  public deleteMem(id:number): Observable<any> {
    return this.http.post(this.memDelete,{id:id});
  }
  public borrowBook(isbn:number, returnDays: Number,borrower:String): Observable<any> {
    return this.http.post(this.borrowUrl,{isbn:isbn, returnDays:returnDays,borrower:borrower});
  }
  public borrowDVD(isbn:number, returnDays: Number,borrower:String): Observable<any> {
    return this.http.post(this.borrowDVDUrl,{isbn:isbn, returnDays:returnDays,borrower:borrower});
  }
  public reserveBook(isbn:number, returnDays: Number,borrower:String): Observable<any> {
    return this.http.post(this.reserveBookUrl,{isbn:isbn, returnDays:returnDays,borrower:borrower});
  }
  public returnedBook(isbn:number): Observable<any> {
    return this.http.post(this.returnBook,{isbn:isbn});
  }
  public reserveTableData(): Observable<any> {
    return this.http.get(this.reserveDetails);
  }
  public returnedDVD(isbn:number): Observable<any> {
    return this.http.post(this.returnDvd,{isbn:isbn});
  }
  public getBooks(): Observable<any> {
    return this.http.get(this.getAllBooks);
  }
  public getDvds(): Observable<any> {
    return this.http.get(this.getAllDvds);
  }
  public findBook(title:String): Observable<any> {
    return this.http.post(this.searchBook,{title:title});
  }
  public findDvd(title:String): Observable<any> {
    return this.http.post(this.searchDvd,{title:title});
  }

  public getReport(): Observable<any> {
    return this.http.get(this.overdueReport);
  }

  public addMember(id:number,name:String,email:String,phone:number): Observable<any> {
    return this.http.post(this.addNewMember,{id:id,name:name,email:email,phone:phone});
  }

  public getMembers(): Observable<any> {
    return this.http.get(this.getAllMembers);
  }
}
