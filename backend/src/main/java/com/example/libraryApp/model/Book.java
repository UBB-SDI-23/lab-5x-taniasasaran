package com.example.libraryApp.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {
    private @Id @GeneratedValue Long id;   // titlu  autor  an  nr pagini  pret
    private String titleBook;
    private String descriptionTheme;
    private Integer publishYear;
    private Integer numberOfPages;
    private Double sellingPrice;
    @OneToMany(mappedBy = "bookOrigin", fetch = FetchType.LAZY)
    List<Chapter> chaptersList;
    @OneToMany(mappedBy = "bookOrigin", fetch = FetchType.LAZY)
    List<BookAuthor> authorsList;

    public Book(String titleBook, String descriptionTheme, Integer publishYear, Integer numberOfPages, Double sellingPrice, List<Chapter> chaptersList, List<BookAuthor> authorsList) {
        this.titleBook = titleBook;
        this.descriptionTheme = descriptionTheme;
        this.publishYear = publishYear;
        this.numberOfPages = numberOfPages;
        this.sellingPrice = sellingPrice;
        this.chaptersList = chaptersList;
        this.authorsList = authorsList;
    }

    public Book() {}

    public String getTitleBook() {
        return titleBook;
    }
    public void setTitleBook(String title) {
        this.titleBook = title;
    }
    public String getDescriptionTheme() {
        return descriptionTheme;
    }
    public void setDescriptionTheme(String theme) {
        this.descriptionTheme = theme;
    }
    public Integer getPublishYear() {
        return publishYear;
    }
    public void setPublishYear(Integer year) {
        this.publishYear = year;
    }
    public Integer getNumberOfPages() {
        return numberOfPages;
    }
    public void setNumberOfPages(Integer pages) {
        this.numberOfPages = pages;
    }
    public Double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(Double price) {
        this.sellingPrice = price;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Chapter> getChaptersList() { return this.chaptersList; }
    public void setChaptersList(List<Chapter> chaptersList) { this.chaptersList = chaptersList; }
    public List<BookAuthor> getAuthorsList() { return this.authorsList; }
    public void setAuthorsList(List<BookAuthor> authorsList) { this.authorsList = authorsList; }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Book))
            return false;
        Book book = (Book) o;
        return Objects.equals(this.id, book.id) && Objects.equals(this.titleBook, book.titleBook)
                && Objects.equals(this.descriptionTheme, book.descriptionTheme)
                && Objects.equals(this.publishYear, book.publishYear)
                && Objects.equals(this.numberOfPages, book.numberOfPages)
                && Objects.equals(this.sellingPrice, book.sellingPrice)
                && Objects.equals(this.chaptersList, book.chaptersList)
                && Objects.equals(this.authorsList, book.authorsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.titleBook, this.descriptionTheme, this.publishYear, this.numberOfPages, this.sellingPrice, this.chaptersList, this.authorsList);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + this.id + ", title='" + this.titleBook + '\'' + ", theme='" + this.descriptionTheme + '\''
                + ", year='" + this.publishYear + '\'' + ", pages='" + this.numberOfPages + '\'' + ", price='" + this.sellingPrice + '\'' + ", chapters='" + this.chaptersList.toString() + ", authors='" + this.authorsList.toString() +'\'' + '}';
    }
}
