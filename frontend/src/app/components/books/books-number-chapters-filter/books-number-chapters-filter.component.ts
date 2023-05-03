import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookNrChaptersDTO } from 'src/app/dto/BookNrChaptersDTO';
import { BookService } from 'src/app/services/book.service.service';


@Component({
  selector: 'app-books-number-chapters-filter',
  templateUrl: './books-number-chapters-filter.component.html',
  styleUrls: ['./books-number-chapters-filter.component.css']
})
export class BooksNumberChaptersFilterComponent {
      nrChapters: number = 0
      bookNrChapters: BookNrChaptersDTO[] = [];
      constructor(
        private bookService: BookService,
        private route: ActivatedRoute,
        private router: Router
      ) {
    
      }
    
      ngOnInit(): void {
        let nrChaptersString: string | null = this.route.snapshot.paramMap.get('n');
        if(nrChaptersString == null) {
          return;
        }
        this.nrChapters = parseInt(nrChaptersString);
        this.route.queryParams
          .subscribe(
            params => {
              this.bookService.getBooksFilteredNrChapters(this.nrChapters).subscribe((result) => {
                this.bookNrChapters = result;
              });
            }
          );
      }
    
      sortByNrChapters(): void {
        this.bookNrChapters.reverse();
      }
  }
    
  
