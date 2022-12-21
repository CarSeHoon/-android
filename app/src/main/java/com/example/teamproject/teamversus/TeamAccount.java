package com.example.teamproject.teamversus;

import com.example.teamproject.UserAccount;

import java.util.ArrayList;

public class TeamAccount {
    private String idToken;         //Firebase Uid(고유 토큰정보)
    private String emailId;         //이메일 아이디
    private String name;            //이름
    private String score;        //자신 점수
    private String teamToken;
    private String teamName;
    private ArrayList<UserAccount> teamUsers;

    public TeamAccount() { }

    public void setTeamUsers(ArrayList<UserAccount> teamUsers) {
        this.teamUsers = teamUsers;
    }

    public ArrayList<UserAccount> getTeamUsers() {
        return this.teamUsers;
    }

    public String getTeamName() {return teamName;}

    public void setTeamName(String teamName) {this.teamName = teamName;}

    public String getTeamToken() {return teamToken;}

    public void setTeamToken(String teamToken) {this.teamToken = teamToken;}

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


}
