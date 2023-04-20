package com.example.libraryApp.controller;

import com.example.libraryApp.model.Message;
import com.example.libraryApp.model.dto.AccountDTO;
import com.example.libraryApp.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Validated
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public @ResponseBody List<AccountDTO> getAllAccounts() {
        return accountService.getAccounts();
    }
    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    public @ResponseBody ResponseEntity<Message> addAccount(@RequestBody AccountDTO newAccount) {
        try{
            accountService.addAccount(newAccount);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{id}")
    public @ResponseBody ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.findById(id);
        if(accountDTO == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{id}")
    public @ResponseBody ResponseEntity<Message>  updateAccount(@RequestBody AccountDTO newAccount, @PathVariable Long id) {
        try{
            accountService.updateAccountWithId(id, newAccount);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
