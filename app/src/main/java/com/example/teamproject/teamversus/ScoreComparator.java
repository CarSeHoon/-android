package com.example.teamproject.teamversus;

import com.example.teamproject.UserAccount;

import java.util.Comparator;

public class ScoreComparator implements Comparator<UserAccount> {
    @Override
    public int compare(UserAccount userAccount, UserAccount t1) {
        if (Integer.parseInt(userAccount.getScore()) < Integer.parseInt(t1.getScore())) {
            return 1;
        } else if (Integer.parseInt(userAccount.getScore()) > Integer.parseInt(t1.getScore())) {
            return -1;
        }
        return 0;
    }
}
