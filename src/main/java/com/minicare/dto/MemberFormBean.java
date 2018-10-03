package com.minicare.dto;

import javax.servlet.http.HttpServletRequest;


public class MemberFormBean implements ValidationForm {
    private String memberId;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String address;
    private String type;
    private String password;
    private String password2;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public boolean validate(HttpServletRequest req) {
        boolean status=true;

        if("".equals(firstname)){
            req.setAttribute("FirstNameError","First Name has to be entered");
            status=false;
        }else if(!(firstname.trim().matches("^[A-Za-z]+$"))){
            req.setAttribute("FirstNameError","Invalid input");
            status=false;
        }

        if("".equals(lastname)){
            req.setAttribute("LastNameError","Last Name has to be entered");
            status=false;
        }else if(!(lastname.trim().matches("^[A-Za-z]+$"))){
            req.setAttribute("LastNameError","Invalid input");
            status=false;
        }

        if("".equals(phonenumber)){
            req.setAttribute("PhoneNumberError","Phone number has to be entered");
            status=false;
        }else if(phonenumber.trim().length()!=10){
            req.setAttribute("PhoneNumberError","Phone number must have 10 digits only");
            status=false;
        }else{
            try{
                Long.parseLong(phonenumber);
            }catch(NumberFormatException e){
                req.setAttribute("PhoneNumberError","Must contain digits only");
                status=false;
            }
        }

        if("".equals(email)){
            req.setAttribute("EmailError","Email Address has to be entered");
            status=false;
        }else if(!email.matches("^[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z]+)*(\\.[a-z]{2,})$")){
            req.setAttribute("EmailError","Email format is invalid");
            status=false;
        }

        if("".equals(address)){
            req.setAttribute("AddressError","Address has to be entered");
            status=false;
        }

        if("".equals(password)){
            req.setAttribute("PasswordError","Password has to be entered");
            status=false;
        }

        if("".equals(password2)){
            req.setAttribute("Password2Error","This field cannot be empty");
            status=false;
        }else if((password != null) && !password.equals(password2)){
            req.setAttribute("Password2Error","Passwords do not match");
            status=false;
        }

        return status;
    }
}
