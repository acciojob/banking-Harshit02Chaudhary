package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount() {
    }

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(sum < 0 || sum > 9*digits) {
            throw new Exception ("Account Number can not be generated");
        }
        StringBuilder accountNumber = new StringBuilder();
        int remainingSum = sum;
        for(int i=0; i<digits; i++) {
            if(remainingSum > 9) {
                accountNumber.append(9);
                remainingSum -= 9;
            }
            else {
                accountNumber.append(remainingSum);
                remainingSum = 0;
            }
        }

        return accountNumber.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.balance < amount) {
            throw new Exception ("Insufficient Balance");
        }
        else {
            this.balance -= amount;
        }
    }

}