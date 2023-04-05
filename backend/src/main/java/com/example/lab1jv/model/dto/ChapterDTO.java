package com.example.lab1jv.model.dto;

import com.example.lab1jv.model.Book;
import com.example.lab1jv.model.Chapter;
import jakarta.persistence.*;

public class ChapterDTO {
    private Long id;   // titlu  autor  an  nr pagini  pret
    private String titleChapter;
    private Integer numberOfPages;
    private Double scoreRating;
    private String description;
    private String mainCharacter;
    private Long bookId;

    public ChapterDTO(Long id, String titleChapter, String description, Integer pages, Double score, String mainCharacter, Long bookId) {
        this.id = id;
        this.titleChapter = titleChapter;
        this.description = description;
        this.numberOfPages = pages;
        this.scoreRating = score;
        this.mainCharacter = mainCharacter;
        this.bookId = bookId;
    }

    public ChapterDTO() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleChapter() {
        return titleChapter;
    }

    public void setTitleChapter(String title) {
        this.titleChapter = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(String character) {
        this.mainCharacter = character;
    }

    public Double getScoreRating() {
        return scoreRating;
    }

    public void setScoreRating(Double score) {
        this.scoreRating = score;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer pages) {
        this.numberOfPages = pages;
    }

    public static ChapterDTO fromChapter(Chapter chapter) {
        return new ChapterDTO(
                chapter.getId(),
                chapter.getTitleChapter(),
                chapter.getDescription(),
                chapter.getNumberOfPages(),
                chapter.getScoreRating(),
                chapter.getMainCharacter(),
                chapter.getBookOrigin().getId()
        );
    }

    public static Chapter toChapter(ChapterDTO chapterDTO, Book book) {
        Chapter chapter = new Chapter();
        chapter.setId(chapterDTO.getId());
        chapter.setTitleChapter(chapterDTO.getTitleChapter());
        chapter.setDescription(chapterDTO.getDescription());
        chapter.setScoreRating(chapterDTO.getScoreRating());
        chapter.setMainCharacter(chapterDTO.getMainCharacter());
        chapter.setNumberOfPages(chapterDTO.getNumberOfPages());
        chapter.setBookOrigin(book);
        return chapter;
    }
}

