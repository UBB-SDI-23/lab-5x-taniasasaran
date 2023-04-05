package com.example.lab1jv.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "BookAuthor")
public class BookAuthor {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book bookOrigin;
    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private Author authorOrigin;
    private Integer numberOfPages;
    private String subjectApproached;

    public BookAuthor(Book bookOrigin, Author authorOrigin, Integer numberOfPages, String subjectApproached) {
        this.bookOrigin = bookOrigin;
        this.authorOrigin = authorOrigin;
        this.numberOfPages = numberOfPages;
        this.subjectApproached = subjectApproached;
    }

    public BookAuthor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBookOrigin() {
        return bookOrigin;
    }

    public void setBookOrigin(Book bookOrigin) {
        this.bookOrigin = bookOrigin;
    }

    public Author getAuthorOrigin() {
        return authorOrigin;
    }

    public void setAuthorOrigin(Author authorOrigin) {
        this.authorOrigin = authorOrigin;
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

    public void setSubjectApproached(String subjectWritten) {
        this.subjectApproached = subjectWritten;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + id +
                "bookOrigin=" + bookOrigin +
                ", authorOrigin=" + authorOrigin +
                ", numberOfPages=" + numberOfPages +
                ", subjectApproached='" + subjectApproached + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthor that = (BookAuthor) o;
        return Objects.equals(id, that.id) && Objects.equals(bookOrigin, that.bookOrigin) && Objects.equals(authorOrigin, that.authorOrigin) && Objects.equals(numberOfPages, that.numberOfPages) && Objects.equals(subjectApproached, that.subjectApproached);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookOrigin, authorOrigin, numberOfPages, subjectApproached);
    }
}