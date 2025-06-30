package Bank;

import java.util.*;

class Transaction {
    String type;
    double amount;
    Date date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date(); // current timestamp
    }

    @Override
    public String toString() {
        return date + " - " + type + ": ₹" + String.format("%.2f", amount);
    }
}

class Account {
    private String accountNumber;
    private String holderName;
    private double balance;
    private List<Transaction> transactions;
    private static final double INTEREST_RATE = 0.05; // 5% annual interest

    public Account(String accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Deposit must be positive.");
            return;
        }
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        System.out.println("✅ ₹" + amount + " deposited.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Withdrawal must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("❌ Insufficient balance.");
            return;
        }
        balance -= amount;
        transactions.add(new Transaction("Withdraw", amount));
        System.out.println("✅ ₹" + amount + " withdrawn.");
    }

    public void applyInterest() {
        double monthlyInterest = balance * (INTEREST_RATE / 12);
        balance += monthlyInterest;
        transactions.add(new Transaction("Interest", monthlyInterest));
        System.out.println("✅ Interest of ₹" + String.format("%.2f", monthlyInterest) + " applied.");
    }

    public void printAccountDetails() {
        System.out.println("\n👤 Account Holder: " + holderName);
        System.out.println("📄 Account Number: " + accountNumber);
        System.out.println("💰 Current Balance: ₹" + String.format("%.2f", balance));
    }

    public void printTransactionHistory() {
        System.out.println("\n📜 Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        Account account = new Account(accNo, name);

        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Apply Interest");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ₹");
                    double dep = scanner.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    double wit = scanner.nextDouble();
                    account.withdraw(wit);
                    break;
                case 3:
                    account.printAccountDetails();
                    break;
                case 4:
                    account.printTransactionHistory();
                    break;
                case 5:
                    account.applyInterest();
                    break;
                case 6:
                    System.out.println("🚪 Exiting. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}

