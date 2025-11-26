package BankingManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        Banking banking = new Banking();

        while (true){
            System.out.println("---- Banking Management System----");
            System.out.println("1.Create Account");
            System.out.println("2.Deposit into the Account");
            System.out.println("3.Withdraw from the Account");
            System.out.println("4.Transfer from one Account to Another");
            System.out.println("5.View Account Details");
            System.out.println("6.View Transactions");
            System.out.println("7.Exit");
            System.out.println("Choose an option:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter the Account Owner Name");
                    String accountOwner = scanner.nextLine();

                    System.out.println("Enter the Account Number ");
                    String accountNumber = scanner.nextLine();

                    System.out.println("Enter the initial balance");
                    double balance = scanner.nextDouble();

                    banking.createAccount(accountOwner,accountNumber,balance);
                    break;

                case 2:
                    System.out.println("Enter the Account Number");
                    String accountDeposit = scanner.nextLine();

                    Account depositAcount = banking.findAccount(accountDeposit);
                    if (depositAcount == null){
                        System.out.println("No Account founded");
                        break;
                    }

                    System.out.println("Enter amount to deposit");
                    double deposit = scanner.nextDouble();
                    depositAcount.deposit(deposit);
                    System.out.println("The Deposit was done successfully");
                    break;

                case 3:
                    System.out.println("Enter the Account Number");
                    String accountWithdraw = scanner.nextLine();

                    Account withdrawAccount = banking.findAccount(accountWithdraw);
                    if (withdrawAccount == null){
                        System.out.println("The account does not exist");
                        break;
                    }

                    System.out.println("Enter amount to withdraw");
                    double wihdraw = scanner.nextDouble();

                    if (withdrawAccount.withdraw(wihdraw)){
                        System.out.println("Withdrawal is successful");
                    }else {
                        System.out.println("Not enough funds");
                    }
                    break;

                case 4:
                    System.out.println("Enter the Sender Account Number");
                    String sender = scanner.nextLine();

                    System.out.println("Enter the  Receiver Account Number ");
                    String receiver = scanner.nextLine();

                    System.out.println("Enter amount to transfer");
                    double transferAmount = scanner.nextDouble();

                    banking.transfer(sender,receiver,transferAmount);
                    break;

                case 5:
                    System.out.println("Enter the Account Number");
                    String accountDetail = scanner.nextLine();

                    Account detailAccount = banking.findAccount(accountDetail);
                    if (detailAccount != null){
                        detailAccount.printAccountDetails();
                    }else {
                        System.out.println("Account Not found");
                    }
                    break;

                case 6:
                    System.out.println("Enter the Account Number");
                    String accountTransaction = scanner.nextLine();

                    Account transactionAccount = banking.findAccount(accountTransaction);
                    if (transactionAccount != null){
                        transactionAccount.printTransactions();
                    }else {
                        System.out.println("Account not found");
                    }
                    break;

                case 7:
                    System.out.println("Exiting the program");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }

    }
}
