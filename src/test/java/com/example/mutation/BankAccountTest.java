package com.example.mutation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private static final double DELTA = 0.0001;

    @Test
    void createsAccountWithInitialBalance() {
        BankAccount account = new BankAccount("Naveen", 100);
        assertEquals("Naveen", account.getOwner());
        assertEquals(100, account.getBalance(), DELTA);
    }

    @Test
    void allowsZeroInitialBalance() {
        assertEquals(0, new BankAccount("A", 0).getBalance(), DELTA);
    }

    @Test
    void rejectsInvalidOwner() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null, 10));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("  ", 10));
    }

    @Test
    void rejectsNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("A", -0.01));
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount("A", 50);
        account.deposit(25);
        assertEquals(75, account.getBalance(), DELTA);
    }

    @Test
    void rejectsNonPositiveDeposit() {
        BankAccount account = new BankAccount("A", 50);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-5));
        assertEquals(50, account.getBalance(), DELTA);
    }

    @Test
    void withdrawDecreasesBalance() {
        BankAccount account = new BankAccount("A", 50);
        account.withdraw(20);
        assertEquals(30, account.getBalance(), DELTA);
    }

    @Test
    void canWithdrawEntireBalance() {
        BankAccount account = new BankAccount("A", 50);
        account.withdraw(50);
        assertEquals(0, account.getBalance(), DELTA);
    }

    @Test
    void rejectsOverdraft() {
        BankAccount account = new BankAccount("A", 50);
        assertThrows(IllegalStateException.class, () -> account.withdraw(50.01));
        assertEquals(50, account.getBalance(), DELTA);
    }

    @Test
    void rejectsNonPositiveWithdrawal() {
        BankAccount account = new BankAccount("A", 50);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-1));
    }

    @Test
    void transferMovesMoneyBetweenAccounts() {
        BankAccount from = new BankAccount("A", 100);
        BankAccount to = new BankAccount("B", 10);
        from.transferTo(to, 40);
        assertEquals(60, from.getBalance(), DELTA);
        assertEquals(50, to.getBalance(), DELTA);
    }

    @Test
    void transferToNullThrows() {
        BankAccount from = new BankAccount("A", 100);
        assertThrows(IllegalArgumentException.class, () -> from.transferTo(null, 10));
        assertEquals(100, from.getBalance(), DELTA);
    }
}
