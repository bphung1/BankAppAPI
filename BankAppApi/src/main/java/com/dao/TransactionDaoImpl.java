package com.dao;

import com.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class TransactionDaoImpl implements TransactionDao{
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Transaction> getTransactionByUserId(int userId) throws DataAccessException {
        final String SELECT_ALL = "select * from usertransaction where userId = ?;";

        return jdbc.query(SELECT_ALL,new TransactionMapper(),userId);
    }

    @Override
    public List<Transaction> getTransactionByTransferFrom(int transactionfrom)throws DataAccessException {
        final String SELECT_ALL = "select * from usertransaction where transactionfrom = ?;";

        return jdbc.query(SELECT_ALL,new TransactionMapper(),transactionfrom);
    }

    public static final class TransactionMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(resultSet.getInt("transactionId"));
            transaction.setUserId(resultSet.getInt("userId"));
            transaction.setTransactionfrom(resultSet.getInt("transactionfrom"));
            transaction.setTransactionto(resultSet.getInt("transactionto"));
            transaction.setTransactionAmount(resultSet.getBigDecimal("transactionAmount"));
            transaction.setTransactionTimeStamp(resultSet.getTimestamp("transactionTimeStamp"));
            return transaction;
        }
    }



}
