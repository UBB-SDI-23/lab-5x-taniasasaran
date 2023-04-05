package com.example.lab1jv.model.dto;

import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.BookAuthor;
import com.example.lab1jv.model.Chapter;

import java.util.List;
import java.util.stream.Collectors;

public class BookAvgAgeDTO {
    private Long id;   // titlu  autor  an  nr pagini  pret
    private String titleBook;
    private Integer numberOfPages;
    private Double sellingPrice;
    private String descriptionTheme;
    private Integer publishYear;
    private Float avgAgeAuthors;

    public BookAvgAgeDTO() {}

    public BookAvgAgeDTO(Long id, String titleBook, Integer numberOfPages, Double sellingPrice, String descriptionTheme, Integer publishYear) {
        this.id = id;
        this.titleBook = titleBook;
        this.numberOfPages = numberOfPages;
        this.sellingPrice = sellingPrice;
        this.descriptionTheme = descriptionTheme;
        this.publishYear = publishYear;
    }

    public BookAvgAgeDTO(Long id, BookDTO bookDTO){
        this.titleBook = bookDTO.getTitleBook();
        this.numberOfPages = bookDTO.getNumberOfPages();
        this.sellingPrice = bookDTO.getSellingPrice();
        this.descriptionTheme = bookDTO.getDescriptionTheme();
        this.publishYear = bookDTO.getPublishYear();
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
    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }
    public Integer getNumberOfPages() {
        return numberOfPages;
    }
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public Double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public String getDescriptionTheme() {
        return descriptionTheme;
    }
    public void setDescriptionTheme(String descriptionTheme) {
        this.descriptionTheme = descriptionTheme;
    }
    public Integer getPublishYear() {
        return publishYear;
    }
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }
    public Float getAvgAgeAuthors() {
        return avgAgeAuthors;
    }
    public void setAvgAgeAuthors(Float avgAgeAuthors) {
        this.avgAgeAuthors = avgAgeAuthors;
    }

    public static BookAvgAgeDTO fromBook(Book book) {
        BookAvgAgeDTO bookAvgAgeDTO =  new BookAvgAgeDTO(
                book.getId(),
                book.getTitleBook(),
                book.getNumberOfPages(),
                book.getSellingPrice(),
                book.getDescriptionTheme(),
                book.getPublishYear()
        );
        if(book.getAuthorsList().size() == 0)
            bookAvgAgeDTO.setAvgAgeAuthors(0f);
        else
            bookAvgAgeDTO.setAvgAgeAuthors((book.getAuthorsList().stream().map(bookauthor -> bookauthor.getAuthorOrigin().getAgeYears() ).reduce(0, Integer::sum) / (float) book.getAuthorsList().size()));
        return bookAvgAgeDTO;
    }

    public static Book toBook(BookAvgAgeDTO bookDTO) {
        Book book = new Book();
        book.setTitleBook(bookDTO.getTitleBook());
        book.setDescriptionTheme(bookDTO.getDescriptionTheme());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setSellingPrice(bookDTO.getSellingPrice());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        book.setId(bookDTO.getId());
        return book;
    }

}
