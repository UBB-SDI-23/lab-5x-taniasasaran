import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { BooksOverviewComponent } from './components/books/books-overview/books-overview.component';
import { BookCreateComponent } from './components/books/book-create/book-create.component';
import { BookEditComponent } from './components/books/book-edit/book-edit.component';
import { BookDetailsComponent } from './components/books/book-details/book-details.component';
import { BookDeleteComponent } from './components/books/book-delete/book-delete.component';
import { BookAvgAgeStatisticComponent } from './components/books/book-avg-age-statistic/book-avg-age-statistic.component';
import { BooksNumberChaptersFilterComponent } from './components/books/books-number-chapters-filter/books-number-chapters-filter.component';

const routes: Routes = [
  {path:"",
  component: HomeComponent
  },
  {path:"books",
  component: BooksOverviewComponent
  },
  {
    path: "books/create",
    component: BookCreateComponent
  },
  {
    path: "books/edit/:id",
    component: BookEditComponent
  },
  {
    path:"books/:id",
    component: BookDetailsComponent
  },
  {
    path:"books/:id/edit",
    component: BookEditComponent
  },
  {
    path:"books/:id/delete",
    component: BookDeleteComponent
  },
  {
    path:"books-ordered-avg-age-authors",
    component: BookAvgAgeStatisticComponent
  },
  {
    path:"books-number-chapters-filter/:n",
    component: BooksNumberChaptersFilterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
