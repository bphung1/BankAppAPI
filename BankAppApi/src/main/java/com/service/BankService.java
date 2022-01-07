package com.service;

import com.entities.AccountUser;
import com.entities.Transaction;

import java.util.List;

public interface BankService {
    AccountUser login(String email, String password);
    AccountUser createUser(AccountUser user);
    List<Transaction> getAllTransactionsByUserId(int userId);
    List<Transaction> getAllTransactionsByTransferFrom(int transactionfrom);
}
