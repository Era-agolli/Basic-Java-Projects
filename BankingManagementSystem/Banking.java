package BankingManagementSystem;

import java.util.ArrayList;

public class Banking {

    private ArrayList<Account> accounts = new ArrayList<>();

    public void createAccount(String accountOwner,String accountNumber,double initialBalance){
        accounts.add(new Account(accountOwner,accountNumber,initialBalance));
        System.out.println("Account has been created successfully!");
    }

    public Account findAccount (String acountNumber){
        for (Account account : accounts){
            if (account.getAccountNumber().equals(acountNumber)){
                return account;
            }
        }
        return null;
    }

    public void transfer(String fromAccount,String toAccount,double amount){
        Account sender = findAccount(fromAccount);
        Account receiver = findAccount(toAccount);

        if (sender == null || receiver == null){
            System.out.println("Unable to locate one or both accounts.");
            return;
        }

        if (!sender.withdraw(amount)) {
            System.out.println("Transfer unsuccessful. Please check your available balance.");
            return;
        }
            receiver.deposit(amount);
        sender.addTransactions("Amount of " + amount + " successfully sent to  " + toAccount);
        receiver.addTransactions("Amount of " + amount + " successfully received to " + fromAccount);

        System.out.println("Transfer is successful");
        }
    }

