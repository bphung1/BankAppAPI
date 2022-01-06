package com.service;

import com.dao.UserDao;
import com.entities.AccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BankServiceImpl implements BankService{
    @Autowired
    UserDao userDao;

    @Override
    public AccountUser login(String email, String password) {
        try {
            return userDao.getUser(email, password);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
