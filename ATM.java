/*
 * USER_ID : user
 * PIN : 1234
 */

import java.util.Scanner;
import java.util.ArrayList;

//Main ATM functions 
public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account userAcc = new Account();
        if (userAcc.authenticateUser()) {
            int choice;
            do {
                System.out.println("1. Transactions History");
                System.out.println("2. Balance");
                System.out.println("3. Withdraw");
                System.out.println("4. Deposit");
                System.out.println("5. Transfer");
                System.out.println("6. Quit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        TransactionsHistory.showHistory();
                        System.out.println("--------------------------------------------------");
                        break;
                    case 2:
                        Balance.showBalance(userAcc);
                        System.out.println("--------------------------------------------------");
                        break;
                    case 3:
                        Withdraw.performWithdrawal(userAcc);
                        System.out.println("--------------------------------------------------");
                        break;
                    case 4:
                        Deposit.performDeposit(userAcc);
                        System.out.println("--------------------------------------------------");
                        break;
                    case 5:
                        Transfer.performTransfer(userAcc);
                        System.out.println("--------------------------------------------------");
                        break;
                    case 6:
                        System.out.println("Exiting ATM. Thank you!");
                        System.out.println("--------------------------------------------------");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        System.out.println("--------------------------------------------------");
                }

            } while (choice != 6);
        } else {
            System.out.println("Authentication failed. Exiting ATM.");
        }
    }
}

class Account {
    private static final String CORRECT_USER_ID = "user";
    private static final String CORRECT_PIN = "1234";

    private double balance;

    public Account() {
        this.balance = 1000.0;
    }

    public boolean authenticateUser() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        return userId.equals(CORRECT_USER_ID) && pin.equals(CORRECT_PIN);
    }

    public void balance() {
        System.out.println("Remaining Balance: Rs " + balance);
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: Rs " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: Rs " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(double amount, String recipientAccount) {        
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Transfer successful. Remaining balance: Rs " + balance);
        } else {
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }
}

//Show transaction history
class TransactionsHistory {
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void addToHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    public static void showHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

//Show balance
class Balance {
    public static void showBalance(Account account) {
        account.balance();
    }
}

//Withdraw amount
class Withdraw {
    public static void performWithdrawal(Account account) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();

        account.withdraw(amount);
        TransactionsHistory.addToHistory("Withdrawal: Rs " + amount);
    }
}

//Deposit amount
class Deposit {
    public static void performDeposit(Account account) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();

        account.deposit(amount);
        TransactionsHistory.addToHistory("Deposit: Rs " + amount);
    }
}

//Transfer amount
class Transfer {
    public static void performTransfer(Account account) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter recipient's account number: ");
        String recipientAccount = sc.nextLine();

        System.out.print("Enter transfer amount: ");
        double amount = sc.nextDouble();

        account.transfer(amount, recipientAccount);
        TransactionsHistory.addToHistory("Transfer: Rs " + amount + " to account " + recipientAccount);
    }
}

