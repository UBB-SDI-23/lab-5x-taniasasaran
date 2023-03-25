package com.example.lab1jv.controller;

import com.example.lab1jv.model.dto.AccountDTO;
import com.example.lab1jv.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/accounts")
    public @ResponseBody Iterable<AccountDTO> getAllAccounts() {
        return accountService.getAccounts();
    }
    @PostMapping("/accounts")
    public void addAccount(@RequestBody AccountDTO newAccount) {
        accountService.addAccount(newAccount);
    }
    @GetMapping("/accounts/{id}")
    public @ResponseBody ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.findById(id);
        if(accountDTO == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        }
    }

    @PutMapping("/accounts/{id}")
    public void updateAccount(@RequestBody AccountDTO newAccount, @PathVariable Long id) {
        accountService.updateAccountWithId(id, newAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
