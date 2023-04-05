package com.example.lab1jv.service;
import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.Chapter;
import com.example.lab1jv.model.dto.BookAvgPagesDTO;
import com.example.lab1jv.model.dto.BookDTO;
import com.example.lab1jv.model.dto.ChapterDTO;
import com.example.lab1jv.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
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

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookAvgPagesDTO> getBooksOrderedByAvgPagesPerChapterDesc() {
        List<Book> books = bookRepository.findAll();
        books.sort((b1, b2) -> {
            double avgPages1 = b1.getChaptersList().stream().mapToDouble(Chapter::getNumberOfPages).average().orElse(0);
            double avgPages2 = b2.getChaptersList().stream().mapToDouble(Chapter::getNumberOfPages).average().orElse(0);
            return Double.compare(avgPages2, avgPages1);
        });
        return books.stream().map(BookAvgPagesDTO::fromBook).collect(Collectors.toList());
    }
}
