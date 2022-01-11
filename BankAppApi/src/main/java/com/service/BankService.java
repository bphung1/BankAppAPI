package com.service;

import com.entities.Account;
import com.entities.AccountType;
import com.entities.AccountUser;

import java.util.List;

public interface BankService {
    AccountUser login(String email, String password);
    AccountUser createUser(AccountUser user);
    List<Account> getAllSavingAccountForUser(int userId);
    Account getSavingAccountForUser(int accountNumber);
    Account createSavingAccount(int userId);
    Account updateSavingAccount(Account account);
    Account deleteSavingAccount(Account account);
    Account createCheckingAccount(int userId);
    Account withdrawFromAccount(Account accountFromWithdrawal, AccountType accType);
    Account depositToAccount(Account accountFromDeposit, AccountType accType);


}
