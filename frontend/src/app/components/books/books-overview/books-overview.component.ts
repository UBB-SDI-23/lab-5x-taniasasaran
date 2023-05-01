import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/Book';
import { BookService } from 'src/app/services/book.service.service';

@Component({
  selector: 'app-books-overview',
  templateUrl: './books-overview.component.html',
  styleUrls: ['./books-overview.component.css']
})
export class BooksOverviewComponent {
  books: Book[] = [];
  constructor(
    private bookService: BookService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams
      .subscribe(
        params => {
          this.bookService.getAllBooks().subscribe(result => {
            this.books = result;
          });
        }
      );
  }
}
