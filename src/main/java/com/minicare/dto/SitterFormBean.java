package com.minicare.dto;

import javax.servlet.http.HttpServletRequest;

public class SitterFormBean extends MemberFormBean{
    private String yearsOfExperience;
    private String expectedPay;

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    public boolean validate(HttpServletRequest req){
        boolean status=super.validate(req);

        if(yearsOfExperience.equals("")){
           req.setAttribute("YearsOfExperienceError","Years Of Experience has to be entered");
           status=false;
       }else if(yearsOfExperience.matches("(\\D)*")){
           req.setAttribute("YearsOfExperienceError","Invalid Input");
           status=false;
       }else{
           int num = Integer.parseInt(yearsOfExperience);
           if(num>122){
               req.setAttribute("YearsOfExperienceError","Invalid Input");
               status=false;
           }

       }

        if(expectedPay.equals("")){
            req.setAttribute("ExpectedPayError","Expected pay has to be entered");
            status=false;
        }else if(!expectedPay.trim().matches("^[0-9]+(\\.[0-9]+)*$")){
            req.setAttribute("ExpectedPayError","Invalid Input");
            status=false;
        }else{
//            status=true;
        }

       return status;
    }
}
