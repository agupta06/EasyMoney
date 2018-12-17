package com.resolvebug.app.easymoney;

public class UserDetailsPojo {

    private String email;
    private String paypalEmail;
    private String referralCode;
    private String appliedReferralCode;
    private String pointsEarned;
    private String joiningBonusGiven;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getAppliedReferralCode() {
        return appliedReferralCode;
    }

    public void setAppliedReferralCode(String appliedReferralCode) {
        this.appliedReferralCode = appliedReferralCode;
    }

    public String getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(String pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public String getJoiningBonusGiven() {
        return joiningBonusGiven;
    }

    public void setJoiningBonusGiven(String joiningBonusGiven) {
        this.joiningBonusGiven = joiningBonusGiven;
    }

    public UserDetailsPojo(String email, String paypalEmail, String referralCode, String appliedReferralCode, String pointsEarned, String joiningBonusGiven) {
        this.email = email;
        this.paypalEmail = paypalEmail;
        this.referralCode = referralCode;
        this.appliedReferralCode = appliedReferralCode;
        this.pointsEarned = pointsEarned;
        this.joiningBonusGiven = joiningBonusGiven;
    }

    public UserDetailsPojo(String email, String referralCode, String appliedReferralCode) {
        this.email = email;
        this.referralCode = referralCode;
        this.appliedReferralCode = appliedReferralCode;
    }

    public UserDetailsPojo(String email) {
        this.email = email;
    }

    public UserDetailsPojo() {
    }
}
