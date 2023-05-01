import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { BookCreateComponent } from './components/books/book-create/book-create.component';
import { BookDeleteComponent } from './components/books/book-delete/book-delete.component';
import { BookDetailsComponent } from './components/books/book-details/book-details.component';
import { BookEditComponent } from './components/books/book-edit/book-edit.component';
import { BooksOverviewComponent } from './components/books/books-overview/books-overview.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookAvgPagesStatisticComponent } from './components/books/book-avg-pages-statistic/book-avg-pages-statistic.component';
import { BookAvgAgeStatisticComponent } from './components/books/book-avg-age-statistic/book-avg-age-statistic.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookCreateComponent,
    BookDeleteComponent,
    BookDetailsComponent,
    BookEditComponent,
    BooksOverviewComponent,
    BookCreateComponent,
    BookAvgPagesStatisticComponent,
    BookAvgAgeStatisticComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
