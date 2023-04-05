package com.example.lab1jv.service;

import com.example.lab1jv.model.Author;
import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.BookAuthor;
import com.example.lab1jv.model.Chapter;
import com.example.lab1jv.model.dto.*;
import com.example.lab1jv.repository.AuthorRepository;
import com.example.lab1jv.repository.BookAuthorRepository;
import com.example.lab1jv.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class BookAuthorService {
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public void createBookAuthor(BookAuthorDTO baDTO) {
        bookAuthorRepository.save(BookAuthorDTO.toBookAuthor(baDTO,
                bookRepository.findById(baDTO.getBookId()).get(),
                authorRepository.findById(baDTO.getAuthorId()).get()));
    }

    public void updateBookAuthorWithId(Long bid, Long aid, BookAuthorDTO baDTO) {
        BookAuthor repoBA = getBookAuthorByIds(bid, aid);
        if (repoBA == null) {
            return;
        }
        BookAuthor bookAuthor = BookAuthorDTO.toBookAuthor(baDTO, repoBA.getBookOrigin(), repoBA.getAuthorOrigin());
        bookAuthor.setId(repoBA.getId());
        bookAuthorRepository.save(bookAuthor);
    }

    private BookAuthor getBookAuthorByIds(Long bid, Long aid) {
        for (BookAuthor ba : bookAuthorRepository.findAll()) {
            if (ba.getBookOrigin().getId().equals(bid) && ba.getAuthorOrigin().getId().equals(aid)) {
                return ba;
            }
        }
        return null;
    }

    public void deleteByBothIds(Long bid, Long aid) {
        BookAuthor ba = getBookAuthorByIds(bid, aid);
        if (ba == null) {
            return;
        }
        bookAuthorRepository.deleteById(ba.getId());
    }

    public List<BookAuthorDTO> getBookAuthorsByBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return null;
        }
        return book.getAuthorsList().stream().map(BookAuthorDTO::fromBookAuthor).collect(Collectors.toList());
    }

    public List<AuthorDTO> getAuthorsByBookId(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return null;
        }
        return book.getAuthorsList().stream().map(BookAuthor::getAuthorOrigin).map(AuthorDTO::fromAuthor).collect(Collectors.toList());
    }

    public List<BookDTO> getBooksByAuthorId(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return null;
        }
        return author.getBooksList().stream().map(BookAuthor::getBookOrigin).map(BookDTO::fromBook).collect(Collectors.toList());
    }

    public List<BookAuthorDTO> getBookAuthorsByAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return null;
        }
        return author.getBooksList().stream().map(BookAuthorDTO::fromBookAuthor).collect(Collectors.toList());
    }

    public List<BookAvgAgeDTO> getBooksOrderedByAvgAge() {
        List<Book> books = bookRepository.findAll();
        books.sort((b1, b2) -> {
            double avgAge1 = b1.getAuthorsList().stream().mapToDouble(author -> author.getAuthorOrigin().getAgeYears()).average().orElse(0);
            double avgAge2 = b2.getAuthorsList().stream().mapToDouble(author -> author.getAuthorOrigin().getAgeYears()).average().orElse(0);
            return Double.compare(avgAge1, avgAge2);
        });
        return books.stream().map(BookAvgAgeDTO::fromBook).collect(Collectors.toList());
    }
}
