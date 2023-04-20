package com.example.libraryApp.controller;
import java.util.List;

import com.example.libraryApp.model.dto.AuthorDTO;
import com.example.libraryApp.model.dto.BookAuthorDTO;
import com.example.libraryApp.model.dto.BookDTO;
import com.example.libraryApp.service.AuthorService;
import com.example.libraryApp.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@Validated
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookAuthorService bookAuthorService;
    @RequestMapping(method = RequestMethod.GET, value = "/authors")
    public @ResponseBody List<AuthorDTO> getAllAuthors() {
        return authorService.getAuthors();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/authors/age-filter")
    public @ResponseBody List<AuthorDTO> getAllAuthorsOlderThan(@RequestParam Integer age) {
        return authorService.getAuthorsOlderThan(age);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/authors")
    public void addAuthor(@RequestBody AuthorDTO newAuthor) {
        authorService.addAuthor(newAuthor);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/authors/{id}")
    public @ResponseBody ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        AuthorDTO author = authorService.findById(id);
        if(author == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/authors/{id}")
    public void updateAuthor(@RequestBody AuthorDTO newAuthor, @PathVariable Long id) {
        authorService.updateAuthorWithId(id, newAuthor);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/authors/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        List<BookAuthorDTO> books = bookAuthorService.getBookAuthorsByAuthor(id);
        for(BookAuthorDTO baDTO: books)
            bookAuthorService.deleteByBothIds(baDTO.getBookId(), baDTO.getAuthorId());
        authorService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authors/{id}/books")
    public @ResponseBody ResponseEntity<Iterable<BookDTO>> getBooksByAuthorId(@PathVariable Long id) {
        return new ResponseEntity<>(bookAuthorService.getBooksByAuthorId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authors/{aid}/books")
    public @ResponseBody void addBookToAuthor(@PathVariable("aid") Long aid, @RequestBody BookAuthorDTO bookauthor) {
        bookauthor.setAuthorId(aid);
        bookAuthorService.createBookAuthor(bookauthor);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/authors/{aid}/books/{bid}")
    public @ResponseBody void updateBookToAuthor(@PathVariable("aid") Long aid, @PathVariable("bid") Long bid, @RequestBody BookAuthorDTO bookauthor) {
        bookauthor.setBookId(bid);
        bookauthor.setAuthorId(aid);
        bookAuthorService.updateBookAuthorWithId(bid, aid, bookauthor);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/authors/{aid}/books/{bid}")
    public @ResponseBody void deleteBookFromAuthor(@PathVariable("aid") Long aid, @PathVariable("bid") Long bid) {
        bookAuthorService.deleteByBothIds(bid, aid);
    }
}
