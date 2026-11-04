package com.example.mutation;

/** A minimal bank account with validation rules - exercises conditional and void-call mutators. */
public class BankAccount {

    private final String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner is required");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }

    public void transferTo(BankAccount target, double amount) {
        if (target == null) {
            throw new IllegalArgumentException("Target account is required");
        }
        withdraw(amount);
        target.deposit(amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }
}
