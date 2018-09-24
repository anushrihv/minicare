package com.minicare.dto;

import javax.servlet.http.HttpServletRequest;


public class JobFormBean implements ValidationForm {
    private String jobTitle;
    private String startDateTime;
    private String endDateTime;
    private String payPerHour;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(String payPerHour) {
        this.payPerHour = payPerHour;
    }

    public boolean validate(HttpServletRequest req) {
        boolean status=true;

        if("".equals(jobTitle)){
            req.setAttribute("JobTitleError","Job Title has to be entered");
            status=false;
        }else if(!(jobTitle.trim().matches("^[A-Za-z]+$"))){
            req.setAttribute("JobTitleError","Job Title must have alphabets only");
            status=false;
        }

        if("".equals(startDateTime)){
            req.setAttribute("StartDateTimeError","Start DateTime has to be entered");
            status=false;
        }else if(!startDateTime.trim().matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")){
            req.setAttribute("StartDateTimeError","Invalid format.Please follow the format YYYY-MM-DD HH:MM:SS");
            status=false;
        }

        if("".equals(endDateTime)){
            req.setAttribute("EndDateTimeError","End DateTime has to be entered");
            status=false;
        }else if(!endDateTime.trim().matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")){
            req.setAttribute("EndDateTimeError","Invalid format.Please follow the format YYYY-MM-DD HH:MM:SS");
            status=false;
        }

        if("".equals(payPerHour)){
            req.setAttribute("PayPerHourError","Pay Per Hour has to be entered");
            status=false;
        }else if(payPerHour.matches("(\\D)*")){
            req.setAttribute("PayPerHourError","Invalid Input");
            status=false;
        }

        return status;
    }
}
