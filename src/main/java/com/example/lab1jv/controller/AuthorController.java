package com.example.lab1jv.controller;
import java.util.List;

import com.example.lab1jv.model.dto.AuthorDTO;
import com.example.lab1jv.model.dto.BookAuthorDTO;
import com.example.lab1jv.model.dto.BookDTO;
import com.example.lab1jv.service.AuthorService;
import com.example.lab1jv.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookAuthorService bookAuthorService;
    @GetMapping("/authors")
    public @ResponseBody Iterable<AuthorDTO> getAllAuthors() {
        return authorService.getAuthors();
    }
    @GetMapping(path="/authors/age-filter", produces = "application/json")
    public @ResponseBody Iterable<AuthorDTO> getAllAuthorsOlderThan(@RequestParam Integer age) {
        return authorService.getAuthorsOlderThan(age);
    }
    @PostMapping("/authors")
    public void addAuthor(@RequestBody AuthorDTO newAuthor) {
        authorService.addAuthor(newAuthor);
    }
    @GetMapping("/authors/{id}")
    public @ResponseBody ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        AuthorDTO author = authorService.findById(id);
        if(author == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
    }

    @PutMapping("/authors/{id}")
    public void updateAuthor(@RequestBody AuthorDTO newAuthor, @PathVariable Long id) {
        authorService.updateAuthorWithId(id, newAuthor);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        List<BookAuthorDTO> books = bookAuthorService.getBookAuthorsByAuthor(id);
        for(BookAuthorDTO baDTO: books)
            bookAuthorService.deleteByBothIds(baDTO.getBookId(), baDTO.getAuthorId());
        authorService.deleteById(id);
    }

    @GetMapping("/authors/{id}/books")
    public @ResponseBody ResponseEntity<Iterable<BookDTO>> getBooksByAuthorId(@PathVariable Long id) {
        return new ResponseEntity<>(bookAuthorService.getBooksByAuthorId(id), HttpStatus.OK);
    }

    @PostMapping("/authors/{aid}/books")
    public @ResponseBody void addBookToAuthor(@PathVariable("aid") Long aid, @RequestBody BookAuthorDTO bookauthor) {
        bookauthor.setAuthorId(aid);
        bookAuthorService.createBookAuthor(bookauthor);
    }

    @PutMapping ("/authors/{aid}/books/{bid}")
    public @ResponseBody void updateBookToAuthor(@PathVariable("aid") Long aid, @PathVariable("bid") Long bid, @RequestBody BookAuthorDTO bookauthor) {
        bookauthor.setBookId(bid);
        bookauthor.setAuthorId(aid);
        bookAuthorService.updateBookAuthorWithId(bid, aid, bookauthor);
    }

    @DeleteMapping("/authors/{aid}/books/{bid}")
    public @ResponseBody void deleteBookFromAuthor(@PathVariable("aid") Long aid, @PathVariable("bid") Long bid) {
        bookAuthorService.deleteByBothIds(bid, aid);
    }
}
