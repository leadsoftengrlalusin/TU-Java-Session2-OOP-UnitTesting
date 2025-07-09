package org.example;

import java.util.*;

public class BankApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Account> accounts = new HashMap<>();
    private static boolean firstTime = true;
    private static String accNum = null;

    public static void main(String[] args) {
        int choice;
        initialMessage();

        do {
            if (!firstTime) {
                System.out.println();
            }

            System.out.print("Enter choice: ");
            firstTime = false;
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 2 && choice <= 5 && accNum == null) {
                System.out.print("Enter Account Number: ");
                accNum = scanner.nextLine();
            }

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> computeInterest();
                case 5 -> displayAccount();
                case 6 -> {
                    System.out.println("Logging out...\n");
                    accNum = null;
                    firstTime = true;
                    initialMessage();
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    private static void initialMessage() {
        System.out.println("\n=== Welcome to MyBank ===");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Compute Interest");
        System.out.println("5. Display Account");
        System.out.println("6. Exit");
    }

    private static void createAccount() {
        System.out.print("\nEnter Account Type (savings/checking): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter Account Number: ");
        accNum = scanner.nextLine();

        System.out.print("Enter Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Initial Deposit: ");
        double deposit = scanner.nextDouble();
        scanner.nextLine();

        Account acc;
        if (type.equals("savings")) {
            acc = new SavingsAccount(accNum, name);
        } else if (type.equals("checking")) {
            acc = new CheckingAccount(accNum, name);
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        acc.deposit(deposit);
        accounts.put(accNum, acc);
        System.out.println("Account created successfully!");
    }

    private static void deposit() {
        Account acc = accounts.get(accNum);
        if (acc == null) {
            System.out.println("Account not found. Please create it first.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            acc.deposit(amount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw() {
        Account acc = accounts.get(accNum);
        if (acc == null) {
            System.out.println("Account not found. Please create it first.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            acc.withdraw(amount);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void computeInterest() {
        Account acc = accounts.get(accNum);
        if (acc == null) {
            System.out.println("Account not found. Please create it first.");
            return;
        }

        System.out.println("Computing interest for account " + accNum + "...");
        double interest = acc.computeInterest();
        acc.deposit(interest);
        System.out.printf("Interest earned: %.2f%n", interest);
        System.out.printf("New balance: %.2f%n", acc.getBalance());
    }

    private static void displayAccount() {
        Account acc = accounts.get(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("--- Account Information ---");
        acc.displayDetails();
    }
}
