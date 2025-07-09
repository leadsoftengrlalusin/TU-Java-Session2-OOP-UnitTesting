/**
 * Final Submission, Java Unit OOP1 Project
 */

package org.example;

public class CheckingAccount extends Account {
    private double interestRate = 0.01; // 1%

    public CheckingAccount(String accountNumber, String accountHolder) {
        super(accountNumber, accountHolder);
    }

    @Override
    public double computeInterest() {
        return balance * interestRate;
    }
}