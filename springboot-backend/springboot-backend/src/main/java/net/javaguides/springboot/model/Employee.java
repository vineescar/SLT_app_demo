package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private long phoneNo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "Package_name")
    private String packageName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "Remaining_Data")
    private String remainingData;

    @Column(name = "Password")
    private String password;

    public Employee() {

    }

    public Employee(String firstName, String packageName, String emailId, String remainingData, String password, long phoneNo) {
        this.firstName = firstName;
        this.packageName = packageName;
        this.emailId = emailId;
        this.remainingData = remainingData;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRemainingData() {
        return remainingData;
    }

    public void setRemainingData(String remainingData) {
        this.remainingData = remainingData;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "phoneNo=" + phoneNo +
                ", firstName='" + firstName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", remainingData='" + remainingData + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
