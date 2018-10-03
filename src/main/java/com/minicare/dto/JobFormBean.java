package com.minicare.dto;

import com.minicare.model.Status;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;


public class JobFormBean implements ValidationForm {
    private String id;
    private String jobTitle;
    private String postedBy;
    private String startDateTime;
    private String endDateTime;
    private String payPerHour;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean validate(HttpServletRequest req) {
        boolean status=true;

        if("".equals(jobTitle)){
            req.setAttribute("JobTitleError","Job Title has to be entered");
            status=false;
        }else if(!(jobTitle.trim().matches("^[A-Za-z\\s]+$"))){
            req.setAttribute("JobTitleError","Job Title must have alphabets only");
            status=false;
        }

        if("".equals(startDateTime)){
            req.setAttribute("StartDateTimeError","Start DateTime has to be entered");
            status=false;
        }else if(!startDateTime.trim().matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}([\\.][0-9])*")){
            req.setAttribute("StartDateTimeError","Invalid format.Please follow the format YYYY-MM-DD HH:MM:SS");
            status=false;
        }else{
            Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
            Timestamp timestamp = null;
            try {
                timestamp = Timestamp.valueOf(startDateTime);

                long milliseconds = timestamp.getTime() - currentDateTime.getTime();
                if(milliseconds<0){
                    req.setAttribute("StartDateTimeError","Invalid input . Start Date Time must be greater than current Date Time");
                    status=false;
                }
            }catch (Exception e){
                req.setAttribute("StartDateTimeError","Invalid input");
                status=false;
            }
        }

        if("".equals(endDateTime)){
            req.setAttribute("EndDateTimeError","End DateTime has to be entered");
            status=false;
        }else if(!endDateTime.trim().matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}([\\.][0-9])*")){
            req.setAttribute("EndDateTimeError","Invalid format.Please follow the format YYYY-MM-DD HH:MM:SS");
            status=false;
        }else{
            Timestamp start = null;
            Timestamp end = null;
            try{
                start = Timestamp.valueOf(startDateTime);
                end = Timestamp.valueOf(endDateTime);

                long milliseconds = end.getTime() - start.getTime();
                if(milliseconds<0){
                    req.setAttribute("EndDateTimeError","Invalid input . End Date Time must be greater than Start Date Time");
                    status=false;
                }
            }catch (Exception e){
                req.setAttribute("EndDateTimeError","Invalid input");
                status = false;
            }
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
