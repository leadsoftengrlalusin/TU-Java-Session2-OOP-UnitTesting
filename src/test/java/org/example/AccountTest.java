/**
 * Final Submission, Java Unit OOP1 Project
 */

package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account savings;
    private Account checking;

    @BeforeEach
    void setup() {
        savings = new SavingsAccount("1001", "John Doe");
        checking = new CheckingAccount("1002", "Neneng B");
    }

    @Test
    void testDepositPositiveAmountSavings() {
        savings.deposit(5000);
        assertEquals(5000, savings.getBalance());
    }

    @Test
    void testDepositPositiveAmountChecking() {
        checking.deposit(5000);
        assertEquals(5000, checking.getBalance());
    }

    @Test
    void testDepositInvalidAmountSavings() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            savings.deposit(0);
        });
        assertEquals("Invalid deposit amount.", exception.getMessage());
    }

    @Test
    void testDepositInvalidAmountChecking() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            checking.deposit(0);
        });
        assertEquals("Invalid deposit amount.", exception.getMessage());
    }

    @Test
    void testWithdrawValidAmountSavings() {
        savings.deposit(5000);
        savings.withdraw(100);
        assertEquals(4900, savings.getBalance());
    }

    @Test
    void testWithdrawValidAmountChecking() {
        checking.deposit(5000);
        checking.withdraw(100);
        assertEquals(4900, checking.getBalance());
    }

    @Test
    void testWithdrawZeroOrNegativeAmountSavings() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> savings.withdraw(0));
        assertEquals("Invalid withdrawal amount.", ex.getMessage());
    }

    @Test
    void testWithdrawZeroOrNegativeAmountChecking() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> checking.withdraw(0));
        assertEquals("Invalid withdrawal amount.", ex.getMessage());
    }

    @Test
    void testWithdrawExceedingBalanceSavings() {
        savings.deposit(5000);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> savings.withdraw(5001));
        assertEquals("Insufficient balance.", ex.getMessage());
    }

    @Test
    void testWithdrawExceedingBalanceChecking() {
        checking.deposit(5000);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> savings.withdraw(5001));
        assertEquals("Insufficient balance.", ex.getMessage());
    }

    @Test
    void testComputeInterestSavings() {
        savings.deposit(5000);
        double interest = savings.computeInterest();
        assertEquals(250.0, interest);
    }

    @Test
    void testComputeInterestChecking() {
        checking.deposit(5000);
        double interest = checking.computeInterest();
        assertEquals(50.0, interest);
    }

    @Test
    void testDisplayDetailsOutputSavings() {
        savings.deposit(5000);
        assertDoesNotThrow(() -> savings.displayDetails());
    }

    @Test
    void testDisplayDetailsOutputChecking() {
        savings.deposit(5000);
        assertDoesNotThrow(() -> checking.displayDetails());
    }
}
