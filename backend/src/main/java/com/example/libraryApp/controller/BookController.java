package com.example.libraryApp.controller;
import java.util.List;

import com.example.libraryApp.model.dto.*;
import com.example.libraryApp.service.BookAuthorService;
import com.example.libraryApp.service.BookService;
import com.example.libraryApp.service.ChapterService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@Validated
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookAuthorService bookAuthorService;
    @Autowired
    private ChapterService chapterService;

    @GetMapping(value = "/books")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/books/{id}")
    public @ResponseBody ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        BookDTO bookDTO = bookService.findById(id);
        if(bookDTO != null)
            return new ResponseEntity<>(bookDTO,  HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/books")
    public void addBook(@RequestBody BookDTO newBook) {
        bookService.createBook(newBook);
    }

    @PutMapping(value = "/books/{id}")
    public @ResponseBody void updateBook(@RequestBody BookDTO newBook, @PathVariable Long id) {
        bookService.updateBookWithId(id, newBook);
    }

    @DeleteMapping(value = "/books/{id}")
    public @ResponseBody  void deleteBook(@PathVariable Long id) {
        List<BookAuthorDTO> books = bookAuthorService.getBookAuthorsByBook(id);
        for(BookAuthorDTO baDTO: books)
            bookAuthorService.deleteByBothIds(baDTO.getBookId(), baDTO.getAuthorId());
        List<ChapterDTO> chapters = bookService.getChaptersByBookId(id);
        for(ChapterDTO chapter: chapters)
            chapterService.deleteById(chapter.getId());
        bookService.deleteById(id);
    }

    @GetMapping(value = "/books/{id}/chapters")
    public @ResponseBody ResponseEntity<List<ChapterDTO>> getChaptersBook(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.getChaptersByBookId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/books/{id}/chapters")
    public @ResponseBody void addListOfChaptersToBook(@PathVariable("id") Long id, @RequestBody List<Long> chapterIdsList) {
        List<ChapterDTO> chaptersList = chapterService.getChaptersByIds(chapterIdsList);
        for (ChapterDTO ch : chaptersList) {
            ch.setBookId(id);
            chapterService.addChapter(ch);
        }
    }

    @GetMapping(value = "/books-ordered-avg-age-authors")
    public @ResponseBody List<BookAvgAgeDTO> getBooksOrderedByAvgAgeAuthors() {
        return bookAuthorService.getBooksOrderedByAvgAge();
    }

    @GetMapping(value = "/books-ordered-avg-pages-chapters")
    public @ResponseBody List<BookAvgPagesDTO> getBooksOrderedByAvgPagesChapters() {
        return bookService.getBooksOrderedByAvgPagesPerChapterDesc();
    }

    @GetMapping(value = "/books/{id}/authors")
    public @ResponseBody ResponseEntity<List<AuthorDTO>> getAuthorsByBook(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookAuthorService.getAuthorsByBookId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/books/{bid}/authors")
    public @ResponseBody void addAuthorToBook(@PathVariable("bid") Long bid, @RequestBody BookAuthorDTO bookauthor) {
        bookauthor.setBookId(bid);
        bookAuthorService.createBookAuthor(bookauthor);
    }


    @PutMapping(value = "/books/{bid}/authors/{aid}")
    public @ResponseBody void updateAuthorToBook(@PathVariable("aid") Long aid, @PathVariable("bid") Long bid,  @RequestBody BookAuthorDTO bookauthor) {
        bookauthor.setBookId(bid);
        bookauthor.setAuthorId(aid);
        bookAuthorService.updateBookAuthorWithId(bid, aid, bookauthor);
    }

    @DeleteMapping(value = "/books/{bid}/authors/{aid}")
    public @ResponseBody void deleteAuthorFromBook(@PathVariable("aid") Long aid, @PathVariable("bid") Long bid) {
        bookAuthorService.deleteByBothIds(bid, aid);
    }

}
