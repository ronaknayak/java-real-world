package com.ronak.javarealworld.multithreading.deadlock;

import java.math.BigDecimal;
import java.util.Objects;

public final class BankAccount {

    private final String accountId;
    private BigDecimal balance;

    public BankAccount(String accountId, BigDecimal openingBalance) {
        this.accountId = Objects.requireNonNull(accountId, "accountId must not be null");
        this.balance = Objects.requireNonNull(openingBalance, "openingBalance must not be null");

        if (openingBalance.signum() < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
    }

    public String accountId() {
        return accountId;
    }

    public synchronized void debit(BigDecimal amount) {
        validateAmount(amount);

        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException(
                    "Insufficient balance in account " + accountId);
        }

        balance = balance.subtract(amount);
    }

    public synchronized void credit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    public synchronized BigDecimal balance() {
        return balance;
    }

    private void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");

        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }
}