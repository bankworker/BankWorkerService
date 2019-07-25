package com.johnny.bankworker.dto;

public class BankBranchStaffDTO extends BaseDTO {
    private Integer staffID;
    private Integer bankID;
    private Integer branchID;
    private String staffName;
    private String staffCellphone;
    private Integer staffPostID;
    private String staffPhotoUrl;
    private String staffResumeUrl;

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getBankID() {
        return bankID;
    }

    public void setBankID(Integer bankID) {
        this.bankID = bankID;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public void setBranchID(Integer branchID) {
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

    public Integer getStaffPostID() {
        return staffPostID;
    }

    public void setStaffPostID(Integer staffPostID) {
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
}
