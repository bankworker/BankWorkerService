package com.johnny.bankworker.entity;

public class BankBranchStaffEntity extends BaseEntity {
    private int staffID;
    private int bankID;
    private String bankName;
    private int branchID;
    private String branchName;
    private String staffName;
    private String staffCellphone;
    private int staffPostID;
    private String staffPostName;
    private String staffPhotoUrl;
    private String staffResumeUrl;

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffCellphone() {
        return staffCellphone;
    }

    public void setStaffCellphone(String staffCellphone) {
        this.staffCellphone = staffCellphone;
    }

    public int getStaffPostID() {
        return staffPostID;
    }

    public void setStaffPostID(int staffPostID) {
        this.staffPostID = staffPostID;
    }

    public String getStaffPhotoUrl() {
        return staffPhotoUrl;
    }

    public void setStaffPhotoUrl(String staffPhotoUrl) {
        this.staffPhotoUrl = staffPhotoUrl;
    }

    public String getStaffResumeUrl() {
        return staffResumeUrl;
    }

    public void setStaffResumeUrl(String staffResumeUrl) {
        this.staffResumeUrl = staffResumeUrl;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStaffPostName() {
        return staffPostName;
    }

    public void setStaffPostName(String staffPostName) {
        this.staffPostName = staffPostName;
    }
}
