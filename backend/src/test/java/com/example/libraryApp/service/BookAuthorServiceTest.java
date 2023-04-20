package com.example.libraryApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.libraryApp.model.*;
import com.example.libraryApp.model.dto.BookAvgAgeDTO;
import com.example.libraryApp.repository.AuthorRepository;
import com.example.libraryApp.repository.BookAuthorRepository;
import com.example.libraryApp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class BookAuthorServiceTest {
    @Mock
    private BookAuthorService bookAuthorService;
    @Mock
    private BookAuthorRepository bookAuthorRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    private List<Book> books;
    private List<Author> authors;
    private List<Account> accounts;
    private List<BookAuthor> bookauthors;

    @BeforeEach
    public void setUp() throws RuntimeException {
        this.accounts = Arrays.asList(new Account("account1", "long-enough-password", "account@mail.com", null),
                new Account("account2", "long-enough-password", "account@mail.com", null),
                new Account("account3", "long-enough-password", "account@mail.com", null));
        for (long i = 0; i < accounts.size(); i++) {
            accounts.get((int) i).setId(i + 1);
        }
        this.authors = Arrays.asList(new Author("firstname1", "lastname", "country", 23, 2010, Arrays.asList(), accounts.get(0)),
                new Author("firstname2", "lastname", "country", 19, 2010, Arrays.asList(), accounts.get(1)),
                new Author("firstname3", "lastname", "country", 70, 2010, Arrays.asList(), accounts.get(2)));
        this.books = Arrays.asList(new Book("title1", "description", 2023, 50, 30.1, Arrays.asList(), Arrays.asList()),
                new Book("title2", "description", 2023, 50, 30.1, Arrays.asList(), Arrays.asList()),
                new Book("title3", "description", 2023, 50, 30.1, Arrays.asList(), Arrays.asList()),
                new Book("title4", "description", 2023, 50, 30.1, Arrays.asList(), Arrays.asList()));
        for (long i = 0; i < books.size(); i++) {
            books.get((int) i).setId(i + 1);
        }
        for (long i = 0; i < authors.size(); i++) {
            authors.get((int) i).setId(i + 1);
        }
        this.bookauthors = Arrays.asList(
                new BookAuthor(books.get(0), authors.get(1), 10, "nimic"),
                new BookAuthor(books.get(0), authors.get(2), 10, "nimic"),
                new BookAuthor(books.get(1), authors.get(0), 10, "nimic"),
                new BookAuthor(books.get(2), authors.get(1), 10, "nimic"),
                new BookAuthor(books.get(3), authors.get(2), 10, "nimic"));
        for (long i = 0; i < bookauthors.size(); i++) {
            bookauthors.get((int) i).setId(i + 1);
            bookAuthorRepository.save(bookauthors.get((int) i));
        }
        authors.get(0).setBooksList(Arrays.asList(bookauthors.get(2)));
        authors.get(1).setBooksList(Arrays.asList(bookauthors.get(0), bookauthors.get(4)));
        authors.get(2).setBooksList(Arrays.asList(bookauthors.get(1), bookauthors.get(3)));
        books.get(0).setAuthorsList(Arrays.asList(bookauthors.get(0), bookauthors.get(1)));
        books.get(1).setAuthorsList(Arrays.asList(bookauthors.get(2)));
        books.get(2).setAuthorsList(Arrays.asList(bookauthors.get(3)));
        books.get(3).setAuthorsList(Arrays.asList(bookauthors.get(4)));

        when(bookRepository.findAll()).thenReturn(books);
        this.bookAuthorService = new BookAuthorService(bookAuthorRepository, bookRepository, authorRepository);
    }
    @Test
    public void testGetBooksOrderedByAvgAge() {
        List<BookAvgAgeDTO> books = bookAuthorService.getBooksOrderedByAvgAge();
        assertNotNull(books);
        assertEquals(Arrays.asList((float)19, (float)23, (float)44.5, (float)70), books.stream().map(BookAvgAgeDTO::getAvgAgeAuthors).collect(Collectors.toList()));
        assertEquals(Arrays.asList("title3", "title2", "title1", "title4"), books.stream().map(BookAvgAgeDTO::getTitleBook).collect(Collectors.toList()));
    }
}
