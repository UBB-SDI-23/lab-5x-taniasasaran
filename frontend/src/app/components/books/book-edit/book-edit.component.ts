import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Book } from 'src/app/model/Book';
import { BookService } from 'src/app/services/book.service.service';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
export class BookEditComponent {
  book: Book = {
    id: 0,
    titleBook: "",
    descriptionTheme: "",
    publishYear: 0,
    numberOfPages: 0,
    sellingPrice: 0,
  };
  editForm = this.formBuilder.group(
    {
      id: [{value: '', disabled: true}],
      title: ['', Validators.required],
      theme: ['', Validators.required],
      publishYear: ['', Validators.required],
      pages: ['', Validators.required],
      price: ['', Validators.required],
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
    if(this.editForm.valid) {
      this.bookService.editBook(this.book).subscribe({
        next: response => {
          this.serverResponse="Ok";
        },
        error: error => {
          this.serverResponse="Error";
        }
      });
    }
  }

  goBack(): void {
    this.location.back();
  }

  goToBooks(): void {
    this.router.navigate(["/books"]);
  }

}
