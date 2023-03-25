package com.example.lab1jv.service;
import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.dto.BookDTO;
import com.example.lab1jv.model.dto.ChapterDTO;
import com.example.lab1jv.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Iterable<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(BookDTO::fromBook).collect(Collectors.toList());
    }

    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            return null;
        }
        return BookDTO.fromBook(book);
    }

    public void createBook(BookDTO bookDTO) {
        bookRepository.save(BookDTO.toBook(bookDTO));
    }

    public void updateBookWithId(Long id, BookDTO bookDTO ) {
        Book repoBook = bookRepository.findById(id).orElse(null);
        Book book = BookDTO.toBook(bookDTO);
        if(repoBook == null){
            return;
        }
        book.setId(repoBook.getId());
        bookRepository.save(book);
    }

    public List<ChapterDTO> getChaptersByBookId(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            return new ArrayList<>();
        }
        return book.getChaptersList().stream().map(ChapterDTO::fromChapter).collect(Collectors.toList());
    }

//    public List<BookDTO> getBooksByAuthorId(Long id){
//        Book book = bookRepository.findById(id).get();
//        if(book == null){
//            return new ArrayList<>();
//        }
//        return book.getAuthorsList().stream().map(author -> AuthorDTO.fromAuthor(author.getAuthorOrigin())).collect(Collectors.toList());
//
//        for(Long author : bookDTO.getAuthorIdsList()){
//            if(author.equals(authorId))
//                return new ResponseEntity<>(bookDTO,  HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
