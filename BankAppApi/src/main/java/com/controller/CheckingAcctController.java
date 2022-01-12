package com.controller;

import com.entities.Account;
import com.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingAcctController extends BaseController{
    @Autowired
    BankService service;

    @PostMapping("/createCheckingAccount/{userId}")
    public ResponseEntity<Account> createCheckAcct(@PathVariable int userId) {
        Account checkingAccount = service.createCheckingAccount(userId);
        if(checkingAccount == null) {
            return new ResponseEntity("Checking account could not be created", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(checkingAccount);
    }
}
