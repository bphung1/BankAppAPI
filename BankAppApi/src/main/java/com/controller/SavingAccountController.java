package com.controller;

import com.entities.Account;
import com.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SavingAccountController extends BaseController{

    @Autowired
    BankService bankService;

    @GetMapping("/savingaccounts")
    public ResponseEntity<List<Account>> getAllUserSavingAccount(@PathVariable int userid){
       try {
           List<Account> list= bankService.getAllSavingAccountForUser(userid);
           return ResponseEntity.ok(list);
       }catch (DataAccessException ex){
           return new ResponseEntity("user id not found", HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/savingaccount")
    public ResponseEntity<Account> getUserSavingAccount(@PathVariable int userAccount){
        try {
            Account account= bankService.getSavingAccountForUser(userAccount);
            return ResponseEntity.ok(account);
        }catch (DataAccessException ex){
            return new ResponseEntity("user account not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/savingaccount")
    public ResponseEntity<Account> updateSavingAccount(@RequestBody A)



}
