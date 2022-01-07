package com.service;

import com.entities.AccountUser;

public interface BankService {
    AccountUser login(String email, String password);
    AccountUser createUser(AccountUser user);
}
