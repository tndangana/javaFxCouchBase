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

@Data
public class User  extends BaseId{
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNumber;
    private Boolean verified = Boolean.FALSE;

    public User() {

    }

    public User(String firstName, String lastName, String email, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public User(String firstName, String lastName, String email, String mobileNumber, Boolean verified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.verified = verified;
    }


    public User(String firstName, String lastName, String email, String password, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
    
    

    
    
    
}
