/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author bignerd
 */

public class User {
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNumber;
    private Boolean verified = Boolean.FALSE;

    public User() {

    }

    public User(String firstName, String lastName, String email, String mobileNumber, Boolean verified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.verified = verified;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public User(String firstName, String lastName, String email, String password, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
    
    
    
    public User save(){
        User user = new User();
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setLastName(lastName);
        user.setMobileNumber(mobileNumber);
        user.setPassword(password);
        user.setVerified(verified);
        return user;
    }
    
    
    
}
