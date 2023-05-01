import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookAvgAgeDTO } from 'src/app/dto/BookAvgAgeDTO';
import { BookService } from 'src/app/services/book.service.service';

@Component({
  selector: 'app-book-avg-age-statistic',
  templateUrl: './book-avg-age-statistic.component.html',
  styleUrls: ['./book-avg-age-statistic.component.css']
})
export class BookAvgAgeStatisticComponent {

    bookAvgAges: BookAvgAgeDTO[] = [];
    constructor(
      private bookService: BookService,
      private activatedRoute: ActivatedRoute,
      private router: Router
    ) {
  
    }
  
    ngOnInit(): void {
      this.activatedRoute.queryParams
        .subscribe(
          params => {
            this.bookService.getBooksAvgAge().subscribe((result) => {
              this.bookAvgAges = result;
            });
          }
        );
    }
  
    sortByAvgAge(): void {
      this.bookAvgAges.reverse();
    }
}
  
