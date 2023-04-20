package com.example.libraryApp.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Author")
public class Author {
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String countryOfOrigin;
    private Integer ageYears;
    private Integer yearOfDebut;
    @OneToMany(mappedBy = "authorOrigin", fetch = FetchType.LAZY)
    List<BookAuthor> booksList;
    @OneToOne(mappedBy = "author")
    private Account accountAuthor;

    public Author(String firstName, String lastName, String countryOfOrigin, Integer ageYears, Integer yearOfDebut, List<BookAuthor> booksList, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryOfOrigin = countryOfOrigin;
        this.ageYears = ageYears;
        this.yearOfDebut = yearOfDebut;
        this.booksList = booksList;
        this.accountAuthor = account;
    }

    public Author() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getCountryOfOrigin() { return countryOfOrigin; }
    public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }
    public Integer getAgeYears() { return ageYears; }
    public void setAgeYears(Integer ageYears) { this.ageYears = ageYears; }
    public Integer getYearOfDebut() { return yearOfDebut; }
    public void setYearOfDebut(Integer yearOfDebut) { this.yearOfDebut = yearOfDebut; }
    public List<BookAuthor> getBooksList() { return booksList; }
    public void setBooksList(List<BookAuthor> booksList) { this.booksList = booksList; }
    public Account getAccount() { return accountAuthor; }
    public void setAccount(Account account) { this.accountAuthor = account; }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", ageYears=" + ageYears +
                ", yearOfDebut=" + yearOfDebut +
                ", booksList=" + booksList +
                ", account=" + accountAuthor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(id, author.id) && Objects.equals(firstName, author.firstName)
                && Objects.equals(lastName, author.lastName)
                && Objects.equals(countryOfOrigin, author.countryOfOrigin)
                && Objects.equals(ageYears, author.ageYears)
                && Objects.equals(yearOfDebut, author.yearOfDebut)
                && Objects.equals(booksList, author.booksList)
                && Objects.equals(accountAuthor, author.accountAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, countryOfOrigin, ageYears, yearOfDebut, booksList, accountAuthor);
    }
}
