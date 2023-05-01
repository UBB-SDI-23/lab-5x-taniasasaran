import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-book-delete',
  templateUrl: './book-delete.component.html',
  styleUrls: ['./book-delete.component.css']
})


export class BookDeleteComponent {
  bookId: number = 0
  serverResponse: String|null = null;
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
    this.bookId = parseInt(bookIdString);
  }

  goBack(): void {
    this.location.back();
  }

  goToBooks(): void {
    this.router.navigate(["/books"]);
  }

  deleteBook(): void {
    this.bookService.deleteBook(this.bookId).subscribe({
      next: response => {
        this.serverResponse="Operation was succesful!";
      },
      error: error => {
        this.serverResponse="An error occured!";
      }
    });
  }
}
