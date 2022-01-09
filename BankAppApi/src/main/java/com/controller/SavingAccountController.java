package com.controller;

import com.entities.Account;
import com.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

@RestController
public class SavingAccountController extends BaseController{

    @Autowired
    BankService bankService;


    @PostMapping("/savingaccount/create/{userid}")
    public ResponseEntity<Account> createSavingAccount(@PathVariable int userid){
      try {
        Account account=bankService.createSavingAccount(userid);
        return ResponseEntity.ok(account);
      }catch (DataAccessException ex){
          return new ResponseEntity("Saving account could not be created",HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/savingaccounts/{userid}")
    public ResponseEntity<List<Account>> getAllUserSavingAccount(@PathVariable int userid){
       try {
           List<Account> list= bankService.getAllSavingAccountForUser(userid);
           return ResponseEntity.ok(list);
       }catch (DataAccessException ex){
           return new ResponseEntity("user id not found", HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/savingaccount/{accountNumber}")
    public ResponseEntity<Account> getUserSavingAccount(@PathVariable int accountNumber){
        try {
            Account account= bankService.getSavingAccountForUser(accountNumber);
            return ResponseEntity.ok(account);
        }catch (DataAccessException ex){
            return new ResponseEntity("user account not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/savingaccount/update")
    public ResponseEntity<Account> updateSavingAccount(@RequestBody Account account){

        try {
            Account account1=bankService.updateSavingAccount(account);
            return ResponseEntity.ok(account1);
        }catch (DataAccessException ex){
            return new ResponseEntity("could not update account",HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/savingaccount/delete/{accountNumber}")
    public ResponseEntity<Account> deleteAccount(@PathVariable int accountNumber){
        try {
            Account account=bankService.getSavingAccountForUser(accountNumber);
            Account account1 = bankService.deleteSavingAccount(account);
            return ResponseEntity.ok(account1);
        }catch (DataAccessException  ex){
            return new ResponseEntity("could not delete account",HttpStatus.NOT_FOUND);
        }
    }



}
