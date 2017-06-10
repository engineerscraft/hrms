package com.hamdard.hua.repository;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Account;
import com.hamdard.hua.rowmapper.AccountRowMapper;

@Component
public class AccountRepository {

    private static final Logger logger = LogManager.getLogger(AccountRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonRepository commonRepository;

    @Value("${sql.allAccountFetching}")
    private String allAccountFetchingSql;

    @Value("${sql.accountUpdate}")
    private String accountUpdateSql;

    @Value("${sql.accountDelete}")
    private String accountDeleteSql;

    @Value("${sql.accountCreate}")
    private String accountCreateSql;

    @Value("${sql.saveActivityHistory}")
    private String saveActivityHistorySql;

    public List<Account> getAllAccounts() {
        try {
            logger.info(sqlMarker, allAccountFetchingSql);
            List<Account> accountList = (List<Account>) jdbcTemplate.query(allAccountFetchingSql, new AccountRowMapper());
            logger.debug("Retrieved accounts: {}", () -> accountList);
            return accountList;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new InternalServerErrorException();
        }
    }
    
    public void updateAccountDetails(Account account, String accountCode, String username) {
        try {
            logger.info(sqlMarker, accountUpdateSql);
            logger.info(sqlMarker, "Params {}, {}, {}", () -> accountCode, () -> account.getAccountName(), () -> account.getAccountType().toString());
            int updated_rows = this.jdbcTemplate.update(accountUpdateSql, account.getAccountName(), account.getAccountType().toString(), accountCode);
            if (updated_rows == 0) {
                logger.error("No account found with code {}", () -> accountCode);
                throw new NotFoundException();
            }
            commonRepository.saveActivityHistory("Details of account " + accountCode + "changed to '" + account.getAccountName() + "' and '" + account.getAccountName(), username);
        } catch (Exception e) {
            logger.error("Account with code {} could not be updated", () -> accountCode);
            logger.error(e);
            throw new InternalServerErrorException();
        }

    }

    public void deleteAccount(String accountCode, String username) {
        try {
            logger.info(sqlMarker, accountDeleteSql);
            logger.info(sqlMarker, "Params {}", () -> accountCode);
            int deleted_rows = this.jdbcTemplate.update(accountDeleteSql, accountCode);
            if (deleted_rows == 0) {
                logger.error("No account found with code {}", () -> accountCode);
                throw new NotFoundException();
            }
            commonRepository.saveActivityHistory("Account " + accountCode + " deleted", username);
        } catch (Exception e) {
            logger.error("Account {} deleted", () -> accountCode);
            logger.error(e);
            throw new InternalServerErrorException();
        }

    }
    
    public void createAccount(Account account, String username) {
        try {
            logger.info(sqlMarker, accountCreateSql);
            logger.info(sqlMarker, "Params {}, {}, {}", () -> account.getAccountCode(), () -> account.getAccountName(), () -> account.getAccountType().toString());
            this.jdbcTemplate.update(accountCreateSql, account.getAccountCode(), account.getAccountName(), account.getAccountType().toString());
            commonRepository.saveActivityHistory("Account " + account.getAccountCode() + " created", username);
        } catch (DuplicateKeyException e) {
            logger.error("GL Account {} already exists", () -> account.getAccountCode());
            logger.error(e);
            throw new ClientErrorException(409);
        } catch (Exception e) {
            logger.error("Bad request for GL Account {}", () -> account.getAccountCode());
            logger.error(e);
            throw new BadRequestException();
        }
    }
}
