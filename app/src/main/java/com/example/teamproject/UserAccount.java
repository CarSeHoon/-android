package com.example.teamproject;

//사용자 계정 정보 모델 클래스
public class UserAccount {
    private String idToken;         //Firebase Uid(고유 토큰정보)
    private String emailId;         //이메일 아이디
    private String password;        //비밀번호
    private String name;            //이름
    private String Birthday;        //생년월일
    private String match;           //경기수
    private String score;        //자신 점수
    private String profile;
    private boolean isSelected;
    private boolean isShowCheckBox;



    public UserAccount() { }

    public boolean isSelected() {return isSelected;}

    public void setSelected(boolean selected) {isSelected = selected;}

    public boolean isShowCheckBox() {
        return this.isShowCheckBox;
    }

    public void setIsShowCheckBox(boolean isShowCheckBox) {
        this.isShowCheckBox = isShowCheckBox;
    }

    public String getProfile() {return profile;}
    public void setProfile(String profile) {this.profile = profile;}

    public String getScore() {return score;}
    public void setScore(String score) {this.score = score;}

    public String getBirthday() {return Birthday;}
    public void setBirthday(String birthday) {Birthday = birthday;}

    public String getMatch() {return match;}
    public void setMatch(String match) {this.match = match;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getIdToken() {return idToken;}
    public void setIdToken(String idToken) {this.idToken = idToken;}

    public String getEmailId() {return emailId;}
    public void setEmailId(String emailId) {this.emailId = emailId;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public void GroupUserAccount(String idToken, String emailId, String password, String name, String Birthday, String match, String score, String profile) {
        this.emailId = emailId;
        this.name = name;
        this.score = score;
    }
}
