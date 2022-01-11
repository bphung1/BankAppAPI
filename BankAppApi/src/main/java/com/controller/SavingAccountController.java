package com.controller;

import com.entities.Account;
import com.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SavingAccountController extends BaseController{

    @Autowired
    BankService bankService;


    @PostMapping("/savingaccount/create/{userid}")
    public ResponseEntity<Account> createSavingAccount(@PathVariable int userid){
          Account account=bankService.createSavingAccount(userid);
          if (account == null){
          return new ResponseEntity("Saving account could not be created",HttpStatus.NOT_FOUND);
          }
          return ResponseEntity.ok(account);
    }

    @GetMapping("/savingaccounts/{userid}")
    public ResponseEntity<List<Account>> getAllUserSavingAccount(@PathVariable int userid){
           List<Account> list= bankService.getAllSavingAccountForUser(userid);
           if(list.isEmpty()) {
               return new ResponseEntity("user id not found", HttpStatus.NOT_FOUND);
           }
           return ResponseEntity.ok(list);
    }

    @GetMapping("/savingaccount/{accountNumber}")
    public ResponseEntity<Account> getUserSavingAccount(@PathVariable int accountNumber){
        Account account= bankService.getSavingAccountForUser(accountNumber);
         if(account==null) {
             return new ResponseEntity("user account not found", HttpStatus.NOT_FOUND);
         }
          return ResponseEntity.ok(account);
    }

    @PutMapping("/savingaccount/update")
    public ResponseEntity<Account> updateSavingAccount(@RequestBody Account account){
            Account account1=bankService.updateSavingAccount(account);
            if(account==null) {
                return new ResponseEntity("could not update account", HttpStatus.NOT_FOUND);
            }else {
                return ResponseEntity.ok(account1);
            }
    }

    @DeleteMapping("/savingaccount/delete/{accountNumber}")
    public ResponseEntity<Account> deleteAccount(@PathVariable int accountNumber){
            Account account=bankService.getSavingAccountForUser(accountNumber);
            Account account1 = bankService.deleteSavingAccount(account);
        if(account1==null){
            return new ResponseEntity("could not delete account",HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(account1);
    }
}