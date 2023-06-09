package com.example.libraryApp.model.dto;

import com.example.libraryApp.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String countryOfOrigin;
    private Integer ageYears;
    private Integer yearOfDebut;
    private List<Long> bookIdsList;

    public AuthorDTO(Long id, String firstName, String lastName, String countryOfOrigin, Integer ageYears, Integer yearOfDebut, List<Long> bookIdsList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryOfOrigin = countryOfOrigin;
        this.ageYears = ageYears;
        this.yearOfDebut = yearOfDebut;
        this.bookIdsList = bookIdsList;
    }

    public AuthorDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Integer getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(Integer ageYears) {
        this.ageYears = ageYears;
    }

    public Integer getYearOfDebut() {
        return yearOfDebut;
    }

    public void setYearOfDebut(Integer yearOfDebut) {
        this.yearOfDebut = yearOfDebut;
    }

    public List<Long> getBookIdsList() {
        return bookIdsList;
    }

    public void setBookIdsList(List<Long> bookIdsList) {
        this.bookIdsList = bookIdsList;
    }

    public static AuthorDTO fromAuthor(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getCountryOfOrigin(),
                author.getAgeYears(),
                author.getYearOfDebut(),
                author.getBooksList().stream().map(BookAuthor::getBookOrigin).map(Book::getId).collect(Collectors.toList()));
    }

    public static Author toAuthor(AuthorDTO authorDTO){
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setCountryOfOrigin(authorDTO.getCountryOfOrigin());
        author.setAgeYears(authorDTO.getAgeYears());
        author.setYearOfDebut(authorDTO.getYearOfDebut());
        return author;
    }

}
