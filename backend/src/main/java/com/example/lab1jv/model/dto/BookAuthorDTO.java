package com.example.lab1jv.model.dto;


import com.example.lab1jv.model.Author;
import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.BookAuthor;
import com.example.lab1jv.model.Chapter;

import java.util.stream.Collectors;

public class BookAuthorDTO {
    private Long id;
    private Long bookId;
    private Long authorId;
    private Integer numberOfPages;
    private String subjectApproached;

    public BookAuthorDTO(Long id, Long bookId, Long authorId, Integer numberOfPages, String subjectApproached) {
        this.id = id;
        this.bookId = bookId;
        this.authorId = authorId;
        this.numberOfPages = numberOfPages;
        this.subjectApproached = subjectApproached;
    }
    public BookAuthorDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getSubjectApproached() {
        return subjectApproached;
    }

    public void setSubjectApproached(String subjectApproached) {
        this.subjectApproached = subjectApproached;
    }

    public static BookAuthorDTO fromBookAuthor(BookAuthor ba) {
        BookAuthorDTO baDTO = new BookAuthorDTO();
        baDTO.setId(ba.getId());
        baDTO.setBookId(ba.getBookOrigin().getId());
        baDTO.setAuthorId(ba.getAuthorOrigin().getId());
        baDTO.setSubjectApproached(ba.getSubjectApproached());
        baDTO.setNumberOfPages(ba.getNumberOfPages());
        return baDTO;
    }

    public static BookAuthor toBookAuthor(BookAuthorDTO baDTO, Book book, Author author) {
        BookAuthor ba = new BookAuthor();
        ba.setSubjectApproached(baDTO.getSubjectApproached());
        ba.setBookOrigin(book);
        ba.setAuthorOrigin(author);
        ba.setNumberOfPages(baDTO.getNumberOfPages());
        ba.setId(baDTO.getId());
        return ba;
    }

}
