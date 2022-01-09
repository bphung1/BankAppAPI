package com.service;

import com.entities.Account;
import com.entities.AccountUser;

import java.util.List;

public interface BankService {
    AccountUser login(String email, String password);
    AccountUser createUser(AccountUser user);
    Account createSavingAccount();
    List<Account> getAllSavingAccountForUser(int userId);
    Account getSavingAccountForUser(int accountNumber);
    Account updateSavingAccount(Account account);
    Account deleteSavingAccount(Account account);

}
