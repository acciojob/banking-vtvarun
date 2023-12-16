package com.driver;

import java.util.*;
public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name, balance,0);
        this.rate = rate;
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }

    public double getRate(){
        return rate;
    }

    public double getMaxWithdrawalLimit(){
        return maxWithdrawalLimit;
    }

    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(amount >= maxWithdrawalLimit) throw new Exception("Maximum Withdraw Limit Exceed");
        super.withdraw(amount);
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double currAmount = super.getBalance();
        double interest = (currAmount * years * rate)/100;
        return currAmount + interest;
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        double currAmount = super.getBalance();
        for (int i = 0; i < years; i++) {
            for (int j = 0; j < times; j++) {
                double interest = (currAmount * rate) / (100 * times);
                currAmount += interest;
            }
        }
        return currAmount;
    }

}
