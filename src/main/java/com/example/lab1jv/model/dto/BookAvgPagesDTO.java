package com.example.lab1jv.model.dto;

import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.BookAuthor;
import com.example.lab1jv.model.Chapter;

import java.util.List;
import java.util.stream.Collectors;

public class BookAvgPagesDTO {
    private Long id;   // titlu  autor  an  nr pagini  pret
    private String titleBook;
    private Integer numberOfPages;
    private Double sellingPrice;
    private String descriptionTheme;
    private Integer publishYear;
    private Float avgPagesChapters;

    public BookAvgPagesDTO() {}

    public BookAvgPagesDTO(Long id, String titleBook, Integer numberOfPages, Double sellingPrice, String descriptionTheme, Integer publishYear) {
        this.id = id;
        this.titleBook = titleBook;
        this.numberOfPages = numberOfPages;
        this.sellingPrice = sellingPrice;
        this.descriptionTheme = descriptionTheme;
        this.publishYear = publishYear;
    }

    public BookAvgPagesDTO(Long id, BookDTO bookDTO){
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
    public Float getAvgPagesChapters() {
        return avgPagesChapters;
    }
    public void setAvgPagesChapters(Float avgPagesChapters) {
        this.avgPagesChapters = avgPagesChapters;
    }

    public static BookAvgPagesDTO fromBook(Book book) {
        BookAvgPagesDTO bookAvgPagesDTO =  new BookAvgPagesDTO(
                book.getId(),
                book.getTitleBook(),
                book.getNumberOfPages(),
                book.getSellingPrice(),
                book.getDescriptionTheme(),
                book.getPublishYear()
        );
        if(book.getChaptersList().size() == 0)
            bookAvgPagesDTO.setAvgPagesChapters(0f);
        else
            bookAvgPagesDTO.setAvgPagesChapters((book.getChaptersList().stream().map(Chapter::getNumberOfPages).reduce(0, Integer::sum) / (float) book.getChaptersList().size()));
        return bookAvgPagesDTO;
    }

    public static Book toBook(BookAvgPagesDTO bookDTO) {
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
