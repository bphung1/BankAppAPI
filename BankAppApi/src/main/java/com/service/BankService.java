package com.service;

import com.entities.Account;
import com.entities.AccountUser;

public interface BankService {
    AccountUser login(String email, String password);
    AccountUser createUser(AccountUser user);
    Account createCheckingAccount(int userId);
}
