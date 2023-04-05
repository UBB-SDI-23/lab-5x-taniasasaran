package com.example.lab1jv.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Account")
public class Account {
    @Id @GeneratedValue
    private Long id;
    private String usernameAccount;
    private String passwordAccount;
    private String emailAccount;
    @OneToOne
    private Author author;


    public Account(String usernameAccount, String passwordAccount, String emailAccount, Author author) {
        this.usernameAccount = usernameAccount;
        this.passwordAccount = passwordAccount;
        this.emailAccount = emailAccount;
        this.author = author;
    }

    public Account() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsernameAccount() { return usernameAccount; }
    public void setUsernameAccount(String usernameAccount) { this.usernameAccount = usernameAccount; }
    public String getPasswordAccount() { return passwordAccount; }
    public void setPasswordAccount(String passwordAccount) { this.passwordAccount = passwordAccount; }
    public String getEmailAccount() { return emailAccount; }
    public void setEmailAccount(String emailAccount) { this.emailAccount = emailAccount; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", usernameAccount='" + usernameAccount + '\'' +
                ", passwordAccount='" + passwordAccount + '\'' +
                ", emailAccount='" + emailAccount + '\'' +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(usernameAccount, account.usernameAccount) && Objects.equals(passwordAccount, account.passwordAccount) && Objects.equals(emailAccount, account.emailAccount) && Objects.equals(author, account.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usernameAccount, passwordAccount, emailAccount, author);
    }
}
