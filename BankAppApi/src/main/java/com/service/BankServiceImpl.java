package com.service;

import com.dao.AccountDao;
import com.dao.UserDao;
import com.entities.Account;
import com.entities.AccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
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

    //TODO: Bao - add this createCheckingAccount/savingAccount when creating a user
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

    @Override
    public Account getCheckingAccount(int accountNumber) {
        try {
            return checkingAccountDao.getAccount(accountNumber);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Account> getAllCheckingAccountsForUser(int userId) {
        try {
            return checkingAccountDao.getAccountsForUser(userId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Account withdrawFromAccount(Account accountFromWithdrawal, AccountType accType) {

        try {

            switch(accType) {
                case CHECKING:

                    Account accountFromDao = checkingAccountDao.getAccount(accountFromWithdrawal.getAccountNumber());
                    BigDecimal withdrawalAmount = accountFromWithdrawal.getBalance();
                    if (checkWithdrawAmount(accountFromDao, withdrawalAmount)) {
                        accountFromDao.setBalance(accountFromDao.getBalance().subtract(withdrawalAmount));
                        accountFromDao = checkingAccountDao.updateAccount(accountFromDao);
                        return accountFromDao;
                    } else { return null; }


                case SAVING:

                    //TODO: Hatim - put savings account dao methods

                default:
                    return null;
            }

        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public Account depositToAccount(Account accountFromDeposit, AccountType accType) {

        try {

            switch(accType) {
                case CHECKING:

                    Account accountFromDao = checkingAccountDao.getAccount(accountFromDeposit.getAccountNumber());
                    BigDecimal depositAmount = accountFromDeposit.getBalance();
                    if (checkIfNegativeAmount(depositAmount)) {
                        accountFromDao.setBalance(accountFromDao.getBalance().add(depositAmount));
                        accountFromDao = checkingAccountDao.updateAccount(accountFromDao);
                        return accountFromDao;
                    } else { return null; }

                case SAVING:

                    //TODO: Hatim - put savings account dao methods

                default:
                    return null;
            }

        } catch (DataAccessException e) {
            return null;
        }
    }


    //TODO: Tyler - withdraw/deposit/transfer instead of one update

    @Override
    public Account deleteCheckingAccount(Account account) {
        try {
            return checkingAccountDao.deleteAccount(account);
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

    private boolean checkWithdrawAmount(Account accountFromDao, BigDecimal withdrawAmount) {
        if(!checkIfNegativeAmount(withdrawAmount) && accountFromDao.getBalance().compareTo(withdrawAmount) >= 0) {
            return true; //GOOD
        } else {
            return false; //NOT GOOD
        }
    }

    private boolean checkIfNegativeAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return true; //NOT GOOD
        } else {
            return false; //GOOD
        }
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
