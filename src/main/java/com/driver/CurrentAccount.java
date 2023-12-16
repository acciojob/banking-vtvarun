package com.driver;

import java.util.*;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        // calling the constructor of the parent class
        super(name, balance,5000.0);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000) throw new Exception("Insufficient Balance");

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        List<Character> chars = new ArrayList<>();
        for(int i=0; i < tradeLicenseId.length() ; i++){
            chars.add(tradeLicenseId.charAt(i));
        }

        boolean isLicenseValid = isvalideLicense(chars);

        boolean foundValid = false;

        if(isLicenseValid == false){
            for(int i=0; i < 1000 ; i++){
                Collections.shuffle(chars);
                boolean a = isvalideLicense(chars);
                if(a){
                    foundValid = true;
                    break;
                }
            }
        } else {
            foundValid = true;
        }

        if(foundValid){
            StringBuilder sb = new StringBuilder("");

            for(char c : chars){
                sb.append(c);
            }
            tradeLicenseId = sb.toString();
        } else {
            throw new Exception("Valid License can not be generated");
        }

    }

    public boolean isvalideLicense(List<Character> chars){


        for(int i=0; i<chars.size()-1 ; i++){
            if(chars.get(i) == chars.get(i+1)){
                return false;
            }
        }

        return true;
    }

    public String getTradeLicenseId(){
        return tradeLicenseId;
    }

}
