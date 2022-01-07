package com.service;

import com.dao.AccountDao;
import com.dao.UserDao;
import com.entities.Account;
import com.entities.AccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Base64;
import java.util.Random;

@Repository
public class BankServiceImpl implements BankService{
    @Autowired
    UserDao userDao;

    @Autowired
    @Qualifier("checkingAccount")
    AccountDao checkingAccountDao;

    @Override
    public AccountUser login(String email, String password) {
        try {
            AccountUser user = userDao.getUser(email);
            if (password.equals(decryptPassword(user.getPassword()))) {
                user.setPassword("HIDDEN");
                return user;
            }
            return null;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public AccountUser createUser(AccountUser user) {
        try {
            user.setPassword(encryptPassword(user.getPassword()));
            AccountUser newUser = userDao.createUser(user);
            newUser.setPassword("HIDDEN");
            return newUser;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Account createCheckingAccount(int userId) {
        try {
            Account newAccount = new Account();
            newAccount.setUserId(userId);
            newAccount.setAccountNumber(generateAccountNumber());

            return checkingAccountDao.createAccount(newAccount);
        } catch (DataAccessException e) {
            return null;
        }

    }

    private int generateAccountNumber() {
        Random rand = new Random();

        int generatedAccountNumber = rand.nextInt(899999) + 100000;
        while(checkingAccountDao.checkAccountNumber(generatedAccountNumber)) {
            generatedAccountNumber = rand.nextInt(899999) + 100000;
        }

        return generatedAccountNumber;
    }



    private String encryptPassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
    }

    private String decryptPassword(String encryptedPassword) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encryptedPassword);
        return new String(bytes);
    }
}
