package com.example.libraryApp.model.dto;

import com.example.libraryApp.model.Book;
import com.example.libraryApp.model.Chapter;
import com.example.libraryApp.model.BookAuthor;

import java.util.List;
import java.util.stream.Collectors;

public class BookDTO {
    private Long id;   // titlu  autor  an  nr pagini  pret
    private String titleBook;
    private Integer numberOfPages;
    private Double sellingPrice;
    private String descriptionTheme;
    private Integer publishYear;
    List<Long> chapterIdsList;
    List<Long> authorIdsList;

    public BookDTO(Long id, String title, String description, Integer pages, Double price, Integer publishYear, List<Long> idsList, List<Long> authorIdsList) {
        this.id = id;
        this.titleBook = title;
        this.descriptionTheme = description;
        this.numberOfPages = pages;
        this.sellingPrice = price;
        this.publishYear = publishYear;
        this.chapterIdsList = idsList;
        this.authorIdsList = authorIdsList;
    }

    public List<Long> getChapterIdsList() {
        return chapterIdsList;
    }
    public void setChapterIdsList(List<Long> chapterIdsList) {
        this.chapterIdsList = chapterIdsList;
    }
    public BookDTO() {}
    public Integer getPublishYear() {
        return publishYear;
    }
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitleBook() {
        return titleBook;
    }
    public void setTitleBook(String title) {
        this.titleBook = title;
    }
    public String getDescriptionTheme() {
        return descriptionTheme;
    }
    public void setDescriptionTheme(String description) {
        this.descriptionTheme = description;
    }
    public Double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(Double score) {
        this.sellingPrice = score;
    }
    public Integer getNumberOfPages() {
        return numberOfPages;
    }
    public void setNumberOfPages(Integer pages) {
        this.numberOfPages = pages;
    }
    public List<Long> getAuthorIdsList() {return authorIdsList;}
    public void setAuthorIdsList(List<Long> authorIdsList) {this.authorIdsList = authorIdsList;}


    public static BookDTO fromBook(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitleBook(),
                book.getDescriptionTheme(),
                book.getNumberOfPages(),
                book.getSellingPrice(),
                book.getPublishYear(),
                book.getChaptersList().stream().map(Chapter::getId).collect(Collectors.toList()),
                book.getAuthorsList().stream().map(BookAuthor::getId).collect(Collectors.toList())
        );
    }

    public static Book toBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitleBook(bookDTO.getTitleBook());
        book.setDescriptionTheme(bookDTO.getDescriptionTheme());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setSellingPrice(bookDTO.getSellingPrice());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        return book;
    }
}

