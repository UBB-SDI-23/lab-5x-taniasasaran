import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Book } from 'src/app/model/Book';
import { Location } from '@angular/common';
import { BookService } from 'src/app/services/book.service.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})

export class BookDetailsComponent {
  book: Book | null = null;

  constructor(
    private route: ActivatedRoute, 
    private bookService: BookService, 
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {
    let bookIdString: string | null = this.route.snapshot.paramMap.get('id');
    if(bookIdString == null) {
      return;
    }
    this.bookService.getBookById(parseInt(bookIdString)).subscribe(result => {
      this.book = result;
    });
  }

  goBack(): void {
    this.location.back();
  }

  deleteBook(id: number | undefined): void {
    if(id == undefined){
      return;
    }
    this.bookService.deleteBook(id).subscribe({
      next: (response) => {
        this.router.navigate(["/books"]);
      },
      error: (error) => {
        this.router.navigate(["/books"]);
      }
    });
  }
}

