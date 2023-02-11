package com.github.vkvish19.bankaccountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankAccount
{
    @Setter
    @Getter
    private String accountId;

    @Setter
    @Getter
    private String accountName;

    @Setter
    @Getter
    private EnumAccountType accountType;

    @Setter
    @Getter
    private BigDecimal accountBalance;
}
