package org.example;

public class SavingsAccount extends Account {
    private double interestRate = 0.05; // 5%

    public SavingsAccount(String accountNumber, String accountHolder) {
        super(accountNumber, accountHolder);
    }

    @Override
    public double computeInterest() {
        return balance * interestRate;
    }
}
