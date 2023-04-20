package com.example.libraryApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.libraryApp.model.*;
import com.example.libraryApp.model.dto.BookAvgPagesDTO;
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
public class BookServiceTest{
    @Mock
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    private List<Book> books;
    private List<Chapter> chapters;
    @BeforeEach
    public void setUp() throws RuntimeException{
        this.bookService = new BookService(bookRepository);
        this.chapters = Arrays.asList(new Chapter("chapter1", 10, 3.6, "desc", "character", null),
                new Chapter("chapter2", 200, 3.6, "desc", "character", null),
                new Chapter("chapter3", 40, 3.6, "desc", "character", null),
                new Chapter("chapter4", 800, 3.6, "desc", "character", null),
                new Chapter("chapter5", 60, 3.6, "desc", "character", null));
        this.books = Arrays.asList(new Book("title1", "description", 2023, 50, 30.1, Arrays.asList(chapters.get(0)), null),
                new Book("title2", "description", 2023, 50, 30.1, Arrays.asList(chapters.get(1)), null),
                new Book("title3", "description", 2023, 50, 30.1, Arrays.asList(chapters.get(3)), null),
                new Book("title4", "description", 2023, 50, 30.1, Arrays.asList(chapters.get(2), chapters.get(4)), null));
        when(bookRepository.findAll()).thenReturn(books);
    }

    @Test
    public void testGetBooksOrderedByPages(){
        List<BookAvgPagesDTO> books = bookService.getBooksOrderedByAvgPagesPerChapterDesc();
        assertNotNull(books);
        assertEquals(Arrays.asList(800, 200, 50, 10).stream().map(Float::valueOf).collect(Collectors.toList()), books.stream().map(BookAvgPagesDTO::getAvgPagesChapters).collect(Collectors.toList()));
        assertEquals(Arrays.asList("title3", "title2", "title4", "title1"), books.stream().map(BookAvgPagesDTO::getTitleBook).collect(Collectors.toList()));
    }

}
