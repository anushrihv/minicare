package com.minicare.service;

import com.minicare.dto.SeekerFormBean;
import com.minicare.dto.SitterFormBean;

import javax.servlet.http.HttpServletRequest;

public class VisitorService{
    private static VisitorService visitorService;

    static {
        visitorService = new VisitorService();
    }

    private VisitorService(){

    }

    public static VisitorService getInstance(){
        return visitorService;
    }

    public void populateSeekerFormBean(HttpServletRequest req){
        SeekerFormBean seekerFormBean = new SeekerFormBean();
        seekerFormBean.setFirstname(req.getParameter("firstname"));
        seekerFormBean.setLastname(req.getParameter("lastname"));
        seekerFormBean.setPhonenumber(req.getParameter("phonenumber"));
        seekerFormBean.setEmail(req.getParameter("email"));
        seekerFormBean.setAddress(req.getParameter("address"));
        seekerFormBean.setPassword(req.getParameter("password"));
        seekerFormBean.setPassword2(req.getParameter("password2"));
        seekerFormBean.setSpouseName(req.getParameter("spousename"));
        seekerFormBean.setNumberOfChildren(req.getParameter("numberofchildren"));
        req.setAttribute("SeekerFormBean",seekerFormBean);
    }

    public void populateSitterFormBean(HttpServletRequest req){
        SitterFormBean sitterFormBean = new SitterFormBean();
        sitterFormBean.setFirstname(req.getParameter("firstname"));
        sitterFormBean.setLastname(req.getParameter("lastname"));
        sitterFormBean.setPhonenumber(req.getParameter("phonenumber"));
        sitterFormBean.setEmail(req.getParameter("email"));
        sitterFormBean.setAddress(req.getParameter("address"));
        sitterFormBean.setPassword(req.getParameter("password"));
        sitterFormBean.setPassword2(req.getParameter("password2"));
        sitterFormBean.setYearsOfExperience(req.getParameter("yearsofexperience"));
        sitterFormBean.setExpectedPay(req.getParameter("expectedpay"));
        req.setAttribute("SitterFormBean",sitterFormBean);
    }
}
