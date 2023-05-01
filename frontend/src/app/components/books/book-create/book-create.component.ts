import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service.service';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Book } from 'src/app/model/Book';

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.css']
})
export class BookCreateComponent {
  book: Book = {
    id: 0,
    titleBook: "",
    descriptionTheme: "",
    publishYear: 0,
    sellingPrice: 0,
    numberOfPages: 0,
  };
  createForm = this.formBuilder.group(
    {
      titleBook: ['', Validators.required],
      descriptionTheme: ['', Validators.required],
      publishYear: [0, Validators.required],
      sellingPrice: [0, Validators.required],
      numberOfPages: [0, Validators.required],
    }
  );
  serverResponse: string|null = null;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService, 
    private formBuilder: FormBuilder,
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

  onSubmit(): void {
    if(this.createForm.valid) {
      this.bookService.createBook(this.book).subscribe({
        next: response => {
          this.serverResponse="Ok";
        },
        error: error => {
          this.serverResponse="Error";
        }
      });
    }
  }

  goToBooks(): void {
    this.router.navigate(["/books"]);
  }

  goBack(): void {
    this.location.back();
  }

}
