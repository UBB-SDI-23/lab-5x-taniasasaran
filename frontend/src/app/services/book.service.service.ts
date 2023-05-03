import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../model/Book';
import { environment } from '../environment/environment';
import { Observable } from 'rxjs';
import { GenericPage } from '../model/GenericPage';
import { BookAvgPagesDTO } from '../dto/BookAvgPagesDTO';
import { BookAvgAgeDTO } from '../dto/BookAvgAgeDTO';
import { BookCreate } from '../model/BookCreate';
import { BookNrChaptersDTO } from '../dto/BookNrChaptersDTO';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  getAllBooks(): Observable<Book[]> {
    return this.http.get(environment.apiURL + "/books") as Observable<Book[]>;
  }

  // getBooksAvgPages(pageNumber: number, pageSize: number): Observable<GenericPage<BookAvgPagesDTO>> {
  //   return this.http.get<GenericPage<BookAvgPagesDTO>>(environment.apiURL + "/books-avg-page" + `?pageNumber=${pageNumber}` + `&pageSize=${[pageSize]}`);
  // }

  getBooksFilteredNrChapters(n: number): Observable<BookNrChaptersDTO[]> {
    return this.http.get(environment.apiURL + "/books-number-chapters-filter/" + n.toString()) as Observable<BookNrChaptersDTO[]>;
  }

  getBooksAvgAge(): Observable<BookAvgAgeDTO[]> {
    return this.http.get(environment.apiURL + "/books-ordered-avg-age-authors") as Observable<BookAvgAgeDTO[]>;
  }

  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(environment.apiURL + "/books/" + id.toString());
  }

  editBook(book: Book): Observable<any>{
    return this.http.put(environment.apiURL + "/books/" + book.id.toString(), book);
  }

  createBook(book: BookCreate): Observable<any>{
    return this.http.post(environment.apiURL + "/books", book);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(environment.apiURL + "/books/" + id.toString());
  }
}

