package com.minicare.dto;

import javax.servlet.http.HttpServletRequest;

public class SeekerFormBean extends MemberFormBean{
    private String numberOfChildren;
    private String spouseName;

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public boolean validate(HttpServletRequest req){
        boolean status=super.validate(req);


        if(numberOfChildren.equals("")){

        }else if(numberOfChildren.matches("(\\D)*")){
            req.setAttribute("NumberOfChildrenError","Has to be a number");
            status=false;
        }else {
            int num = Integer.parseInt(numberOfChildren);
            if(num>69) {
                req.setAttribute("NumberOfChildrenError", "Invalid Input");
                status = false;
            }
        }

        if(spouseName.equals("")){

        }else if(!(spouseName.trim().matches("^[A-Za-z]+$"))){
            req.setAttribute("SpouseNameError","Spouse Name must have alphabets only");
            status=false;
        }

        return status;
    }
}
