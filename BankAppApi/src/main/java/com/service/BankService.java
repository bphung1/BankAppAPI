package com.service;

import com.entities.Account;
import com.entities.AccountUser;

import java.util.List;

public interface BankService {
    AccountUser login(String email, String password);
    AccountUser createUser(AccountUser user);

    Account createCheckingAccount(int userId);
    Account getCheckingAccount(int accountNumber);
    List<Account> getAllCheckingAccountsForUser(int userId);
    Account updateCheckingAccount(Account account);
    Account deleteCheckingAccount(Account account);
}
