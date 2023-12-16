package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public double getBalance(){
        return balance;
    }

    public String getName(){
        return name;
    }

    public double getMinBalance(){
        return minBalance;
    }


    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        StringBuilder accountNumber = new StringBuilder("");

        boolean canGenerate = canBeGenerated(0, "", digits, sum,accountNumber);

        if(!canGenerate){
            throw new Exception("Account Number can not be generated");
        }

        return accountNumber.toString();
    }

    public boolean canBeGenerated(int currsum, String accountNumberSoFar, int digits, int sum, StringBuilder sb){

        if(currsum > sum) return false;
        if(accountNumberSoFar.length() > digits) return false;

        if(currsum == sum && accountNumberSoFar.length() == digits){
            sb.append(accountNumberSoFar);
            return true;
        }

        for(int i=0; i < 10 ; i++){
            boolean ans = canBeGenerated(currsum+i,accountNumberSoFar+i+"",digits,sum, sb);
            if(ans) return true;
        }

        return false;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if((balance < minBalance) || (balance - amount) < minBalance){
            throw new Exception("Insufficient Balance");
        }
        balance -= amount;
    }

}