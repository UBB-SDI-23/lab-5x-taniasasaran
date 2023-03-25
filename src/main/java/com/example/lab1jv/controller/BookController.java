package com.example.lab1jv.controller;
import java.util.List;
import java.util.stream.Collectors;

import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.BookAuthor;
import com.example.lab1jv.model.dto.*;
import com.example.lab1jv.service.BookAuthorService;
import com.example.lab1jv.service.BookService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookAuthorService bookAuthorService;
    @Autowired
    private ChapterController chapterService;

    @GetMapping("/books")
    Iterable<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public @ResponseBody ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        BookDTO bookDTO = bookService.findById(id);
        if(bookDTO != null)
            return new ResponseEntity<>(bookDTO,  HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path="/books/{id}/chapters", produces = "application/json")
    public @ResponseBody ResponseEntity<List<ChapterDTO>> getChaptersBook(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.getChaptersByBookId(id), HttpStatus.OK);
    }

    @PostMapping("/books")
    public void addBook(@RequestBody BookDTO newBook) {
        bookService.createBook(newBook);
    }

    @PutMapping("/books/{id}")
    public @ResponseBody void updateBook(@RequestBody BookDTO newBook, @PathVariable Long id) {
        bookService.updateBookWithId(id, newBook);
    }

    @DeleteMapping("/books/{id}")
    public @ResponseBody  void deleteBook(@PathVariable Long id) {
        List<BookAuthorDTO> books = bookAuthorService.getBookAuthorsByBook(id);
        for(BookAuthorDTO baDTO: books)
            bookAuthorService.deleteById(baDTO.getId());
        List<ChapterDTO> chapters = bookService.getChaptersByBookId(id);
        for(ChapterDTO chapter: chapters)
            chapterService.deleteChapter(chapter.getId());
        bookService.deleteById(id);
    }

    @GetMapping("/books-ordered-avg-age-authors")
    public @ResponseBody Iterable<BookAvgAgeDTO> getBooksOrderedByAvgAgeAuthors() {
        return bookAuthorService.getBooksOrderedByAvgAge();
    }

    @GetMapping("/books-ordered-avg-pages-chapters")
    public @ResponseBody Iterable<BookAvgPagesDTO> getBooksOrderedByAvgPagesChapters() {
        return bookAuthorService.getBooksOrderedByAvgPagesPerChapterDesc();
    }

    @GetMapping("/books/{id}/authors")
    public @ResponseBody ResponseEntity<List<AuthorDTO>> getAuthorsByBook(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookAuthorService.getAuthorsByBookId(id), HttpStatus.OK);
    }

    @PostMapping("/books/authors")
    public @ResponseBody void addAuthorToBook(@RequestBody BookAuthorDTO author) {
        bookAuthorService.createBookAuthor(author);
    }

    @PutMapping ("/books/{id}/authors")
    public @ResponseBody void updateAuthorToBook(@PathVariable("id") Long id, @RequestBody BookAuthorDTO author) {
        bookAuthorService.updateBookAuthorWithId(id, author);
    }

    @DeleteMapping("/books/{id}/authors")
    public @ResponseBody void deleteAuthorFromBook(@PathVariable("id") Long id) {
        bookAuthorService.deleteById(id);
    }



}
