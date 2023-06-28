package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000) {
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        int n = tradeLicenseId.length();
        boolean valid = true;

        for(int i=0; i<n; i++)
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1))
                valid = false;

        if(valid)
            return;

        int freq[] = new int[26];
        for(int i=0; i<n; i++) {
            freq[tradeLicenseId.charAt(i)-'A']++;
        }

        int max = 0, letter = 0;
        for(int i=0; i<26; i++) {
            if(freq[i] > max) {
                max = freq[i];
                letter = i;
            }
        }

        if(max > (n+1)/2)
            throw new Exception("Valid License can not be generated");

        char validId[] = new char[n];
        //for odd positions
        for(int i=0; i<n; i+=2) {
            if(freq[letter] > 0) {
                validId[i] = (char) (letter + 'A');
                freq[letter]--;
            }
            else {
                for(int j=0; j<26; j++) {
                    if(freq[j] > 0) {
                        letter = j;
                        break;
                    }
                }
                validId[i] = (char) (letter + 'A');
                freq[letter]--;
            }
        }

        //for even positions
        for(int i=1; i<n; i+=2) {
            if(freq[letter] > 0) {
                validId[i] = (char) (letter + 'A');
                freq[letter]--;
            }
            else {
                for(int j=0; j<26; j++) {
                    if(freq[j] > 0) {
                        letter = j;
                        break;
                    }
                }
                validId[i] = (char) (letter + 'A');
                freq[letter]--;
            }
        }

        tradeLicenseId = validId.toString();
    }

}
