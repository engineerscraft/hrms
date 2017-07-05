package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.enums.AccountType;
import com.hamdard.hua.model.Account;

public class AccountRowMapper implements RowMapper<Account> {
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountCode(rs.getString("ACCOUNT_CODE"));
        account.setAccountName(rs.getString("ACCOUNT_NAME"));
        account.setAccountType(AccountType.valueOf(rs.getString("ACCOUNT_TYPE")));
        return account;
    }
}
