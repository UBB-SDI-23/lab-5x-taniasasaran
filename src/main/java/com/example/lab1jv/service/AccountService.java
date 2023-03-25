package com.example.lab1jv.service;

import com.example.lab1jv.model.Account;
import com.example.lab1jv.model.dto.AccountDTO;
import com.example.lab1jv.model.dto.AuthorDTO;
import com.example.lab1jv.repository.AccountRepository;
import com.example.lab1jv.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public AccountRepository getRepo(){ return this.accountRepository; }

    public Iterable<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::fromAccount).collect(Collectors.toList());
    }

    public void addAccount(AccountDTO account) {
        passwordValidation(account.getPassword());
        accountRepository.save(AccountDTO.toAccount(account, authorRepository.findById(account.getAuthorId()).get()));
    }

    private void passwordValidation(String password){
        if(password.length() < 6){
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }

    public AccountDTO findById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null){
            return null;
        }
        return AccountDTO.fromAccount(account);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public void updateAccountWithId(Long id, AccountDTO accountDTO ) {
        Account repoAccount = accountRepository.findById(id).orElse(null);
        if (repoAccount == null) {
            return;
        }
        passwordValidation(accountDTO.getPassword());
        Account account = AccountDTO.toAccount(accountDTO, authorRepository.findById(accountDTO.getAuthorId()).get());
        account.setId(repoAccount.getId());
        accountRepository.save(account);
    }

    public AuthorDTO getAuthorByAccountId(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null){
            return null;
        }
        return AuthorDTO.fromAuthor(account.getAuthor());
    }
}


