package com.example.libraryApp.model.dto;

import com.example.libraryApp.model.Book;

public class BookNrChaptersDTO {
    private Long id;   // titlu  autor  an  nr pagini  pret
    private String titleBook;
    private Integer numberOfPages;
    private Double sellingPrice;
    private String descriptionTheme;
    private Integer publishYear;
    private Integer nrChapters;

    public BookNrChaptersDTO() {}

    public BookNrChaptersDTO(Long id, String titleBook, Integer numberOfPages, Double sellingPrice, String descriptionTheme, Integer publishYear) {
        this.id = id;
        this.titleBook = titleBook;
        this.numberOfPages = numberOfPages;
        this.sellingPrice = sellingPrice;
        this.descriptionTheme = descriptionTheme;
        this.publishYear = publishYear;
    }

    public BookNrChaptersDTO(Long id, BookDTO bookDTO){
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
    public Integer getNrChapters() {
        return nrChapters;
    }
    public void setNrChapters(Integer nrChapters) {
        this.nrChapters = nrChapters;
    }

    public static BookNrChaptersDTO fromBook(Book book) {
        BookNrChaptersDTO bookNrChaptersDTO =  new BookNrChaptersDTO(
                book.getId(),
                book.getTitleBook(),
                book.getNumberOfPages(),
                book.getSellingPrice(),
                book.getDescriptionTheme(),
                book.getPublishYear()
        );
        if(book.getAuthorsList().size() == 0)
            bookNrChaptersDTO.setNrChapters(0);
        else
            bookNrChaptersDTO.setNrChapters((book.getChaptersList().size()));
        return bookNrChaptersDTO;
    }

    public static Book toBook(BookNrChaptersDTO bookDTO) {
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
