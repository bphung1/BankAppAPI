package com.service;

import com.entities.Account;
import com.entities.AccountType;
import com.entities.AccountUser;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {
    AccountUser login(String email, String password);
    AccountUser createUser(AccountUser user);
  
    Account createCheckingAccount(int userId);
    Account getCheckingAccount(int accountNumber);
    List<Account> getAllCheckingAccountsForUser(int userId);
    Account deleteCheckingAccount(Account account);
  
    Account withdrawFromAccount(Account accountFromWithdrawal, AccountType accType);
    Account depositToAccount(Account accountFromDeposit, AccountType accType);
  
    List<Account> getAllSavingAccountForUser(int userId);
    Account getSavingAccountForUser(int accountNumber);
    Account createSavingAccount(int userId);
    Account deleteSavingAccount(Account account);
}
