package com.minicare.dto;

import com.minicare.service.VisitorService;

import javax.servlet.http.HttpServletRequest;

public class LoginFormBean implements ValidationForm{
    private String email;
    private String password;
    private String type;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean validate(HttpServletRequest request){
        boolean status=true;

        if("".equals(email)){
            request.setAttribute("LoginEmailError","Email Address has to be entered");
            status=false;
        }else if(!email.matches("^[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z]+)*(\\.[a-z]{2,})$")){
            request.setAttribute("LoginEmailError","Email format is invalid");
            status=false;
        }

        if("".equals(password)){
            request.setAttribute("LoginPasswordError","Password has to be entered");
            status=false;
        }

        return status;
    }
}
