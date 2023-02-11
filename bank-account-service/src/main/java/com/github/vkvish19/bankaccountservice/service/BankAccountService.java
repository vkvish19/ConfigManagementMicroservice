package com.github.vkvish19.bankaccountservice.service;

import com.github.vkvish19.bankaccountservice.config.Configuration;
import com.github.vkvish19.bankaccountservice.model.BankAccount;
import com.github.vkvish19.bankaccountservice.model.EnumAccountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BankAccountService
{
    @Autowired
    private Configuration configuration;
    private final Map<String, BankAccount> accountsCache = new HashMap<>();

    @PostConstruct
    public void setupTestData()
    {
        BankAccount account1 = new BankAccount("10001", "Mike Ross",
                EnumAccountType.CURRENT_ACCOUNT, BigDecimal.valueOf(1250.38));
        BankAccount account2 = new BankAccount("10002", "Sam Annual",
                EnumAccountType.SAVINGS_ACCOUNT, BigDecimal.valueOf(1550.40));

        accountsCache.put(account1.getAccountId(), account1);
        accountsCache.put(account2.getAccountId(), account2);
    }

    public void createBankAccount(BankAccount account)
    {
        // if balance within limits then add account to cache else throw excption
        if(account.getAccountBalance().doubleValue() >= configuration.getMinBalance()
                && account.getAccountBalance().doubleValue() <= configuration.getMaxBalance())
        {
            log.info("Account balance [{}] is is greater than lower bound [{}] and less than upper bound [{}]",
                    account.getAccountBalance(), configuration.getMinBalance(), configuration.getMaxBalance());
            accountsCache.put(account.getAccountId(), account);
        }
        else
        {
            log.info("Account balance [{}] is outside of lower bound [{}] and upper bound [{}]",
                    account.getAccountBalance(), configuration.getMinBalance(), configuration.getMaxBalance());
            throw new InvalidAccountBalanceException("Bank account balance is outside of allowed thresholds.");
        }
    }

    public BankAccount retrieveBankAccount(String accountId)
    {
        return accountsCache.get(accountId);
    }
}
