package com.resolvebug.app.easymoney;

public class UserDetailsPojo {

    private String email;
    private String phoneNumber;
    private String referralCode;
    private String appliedReferralCode;
    private String referralCount;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getReferralCount() {
        return referralCount;
    }

    public void setReferralCount(String referralCount) {
        this.referralCount = referralCount;
    }

    public String getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(String pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    private String pointsEarned;

    public UserDetailsPojo(String email, String phoneNumber, String referralCode, String appliedReferralCode, String referralCount, String pointsEarned) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.referralCode = referralCode;
        this.appliedReferralCode = appliedReferralCode;
        this.referralCount = referralCount;
        this.pointsEarned = pointsEarned;
    }

    public UserDetailsPojo(String email, String referralCode, String appliedReferralCode) {
        this.email = email;
        this.referralCode = referralCode;
        this.appliedReferralCode = appliedReferralCode;
    }
}
