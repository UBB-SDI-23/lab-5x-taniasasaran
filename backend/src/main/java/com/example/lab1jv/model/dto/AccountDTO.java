package com.example.lab1jv.model.dto;

import com.example.lab1jv.model.Account;
import com.example.lab1jv.model.Author;
import com.example.lab1jv.model.Chapter;

public class AccountDTO {
    private Long id;
    private String usernameAccount;
    private String passwordAccount;
    private String emailAccount;
    private Long authorId;

    public AccountDTO(Long id, String username, String password, String email, Long authorId) {
        this.id = id;
        this.usernameAccount = username;
        this.passwordAccount = password;
        this.emailAccount = email;
        this.authorId = authorId;
    }

    public AccountDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return usernameAccount;
    }

    public void setUsername(String username) {
        this.usernameAccount = username;
    }

    public String getPassword() {
        return passwordAccount;
    }

    public void setPassword(String password) {
        this.passwordAccount = password;
    }

    public String getEmail() {
        return emailAccount;
    }

    public void setEmail(String email) {
        this.emailAccount = email;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setRole(Long authorId) {
        this.authorId = authorId;
    }

    public static AccountDTO fromAccount(Account account) {
        return new AccountDTO(account.getId(),
                account.getUsernameAccount(),
                account.getPasswordAccount(),
                account.getEmailAccount(),
                account.getAuthor().getId());
    }

    public static Account toAccount(AccountDTO accountDTO, Author author){
        Account account = new Account();
        account.setUsernameAccount(accountDTO.getUsername());
        account.setPasswordAccount(accountDTO.getPassword());
        account.setEmailAccount(accountDTO.getEmail());
        account.setAuthor(author);
        account.setId(accountDTO.getId());
        return account;
    }
}
