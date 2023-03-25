package com.example.lab1jv.controller;
import java.util.List;

import com.example.lab1jv.model.Author;
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
            bookAuthorService.deleteById(baDTO.getId());
        authorService.deleteById(id);
    }

    @GetMapping("/authors/{id}/books")
    public @ResponseBody ResponseEntity<Iterable<BookDTO>> getBooksByAuthorId(@PathVariable Long id) {
        return new ResponseEntity<>(bookAuthorService.getBooksByAuthorId(id), HttpStatus.OK);
    }

    @PostMapping("/authors/{id}/books")
    public @ResponseBody void addBookToAuthor(@PathVariable("id") Long id, @RequestBody BookAuthorDTO author) {
        bookAuthorService.createBookAuthor(author);
    }

    @PutMapping ("/authors/{id}/books")
    public @ResponseBody void updateBookToAuthor(@PathVariable("id") Long id, @RequestBody BookAuthorDTO author) {
        bookAuthorService.updateBookAuthorWithId(id, author);
    }

    @DeleteMapping("/authors/{id}/books")
    public @ResponseBody void deleteBookFromAuthor(@PathVariable("id") Long id) {
        bookAuthorService.deleteById(id);
    }
}
