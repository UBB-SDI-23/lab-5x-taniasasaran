package com.example.libraryApp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Chapter")
public class Chapter {
    private @Id @GeneratedValue Long id;   // titlu  autor  an  nr pagini  pret
    private String titleChapter;
    private Integer numberOfPages;
    private Double scoreRating;
    private String description;
    private String mainCharacter;
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book bookOrigin;

    public Chapter(String titleChapter, Integer numberOfPages, Double scoreRating, String description, String mainCharacter, Book bookOrigin) {
        this.titleChapter = titleChapter;
        this.numberOfPages = numberOfPages;
        this.scoreRating = scoreRating;
        this.description = description;
        this.mainCharacter = mainCharacter;
        this.bookOrigin = bookOrigin;
    }

    public Chapter() {}

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id; }
    public String getTitleChapter() { return titleChapter; }
    public void setTitleChapter(String titleChapter) { this.titleChapter = titleChapter; }
    public Integer getNumberOfPages() { return numberOfPages; }
    public void setNumberOfPages(Integer numberOfPages) { this.numberOfPages = numberOfPages; }
    public Double getScoreRating() { return scoreRating; }
    public void setScoreRating(Double scoreRating) { this.scoreRating = scoreRating; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getMainCharacter() { return mainCharacter; }
    public void setMainCharacter(String mainCharacter) { this.mainCharacter = mainCharacter; }
    public Book getBookOrigin() { return bookOrigin; }
    public void setBookOrigin(Book bookId) { this.bookOrigin = bookId; }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", titleChapter='" + titleChapter + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", scoreRating=" + scoreRating +
                ", description='" + description + '\'' +
                ", mainCharacter='" + mainCharacter + '\'' +
                ", bookName=" + bookOrigin.getTitleBook() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id) && Objects.equals(titleChapter, chapter.titleChapter) && Objects.equals(numberOfPages, chapter.numberOfPages) && Objects.equals(scoreRating, chapter.scoreRating) && Objects.equals(description, chapter.description) && Objects.equals(mainCharacter, chapter.mainCharacter) && Objects.equals(bookOrigin, chapter.bookOrigin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleChapter, numberOfPages, scoreRating, description, mainCharacter, bookOrigin);
    }
}
