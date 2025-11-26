package BankingManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accountOwner;
    private String accountNumber;
    private double balance;
    private List<String> transactions = new ArrayList<>();

    public Account(String accountOwner,String accountNumber,double initialBalance){
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        transactions.add("Account is created with balance: " + initialBalance);
    }

    public String getAccountOwner(){
        return accountOwner;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        balance += amount;
        transactions.add("The amount that is deposited is: " + amount + ", The new balance is : " + balance);
    }

    public boolean withdraw(double amount){
        if (amount > balance){
            transactions.add("Unsuccessful withdrawal of  " + amount );
            return false;
        }
       balance -= amount;
        transactions.add("Amount withdrawn: " + amount + " , The new balance is " + balance);
        return true;
    }

    public void addTransactions(String message){
        transactions.add(message);
    }

    public void printTransactions(){
        System.out.println(" Transactions for account " + accountNumber + ":");
        for (String transaction : transactions){
            System.out.println(" - " + transaction);
        }
    }

    public void printAccountDetails(){
        System.out.println("----Account Details----");
        System.out.println("Account Owner : " + accountOwner );
        System.out.println("Account NUmber : " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}
