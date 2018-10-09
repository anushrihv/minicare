package com.minicare.controller.seeker;

import com.minicare.dto.PasswordHashHelper;
import com.minicare.dto.SeekerFormBean;
import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
import com.minicare.model.Type;
import com.minicare.service.SeekerService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SeekerUtil {
    public static SeekerUtil seekerUtil;

    static {
        seekerUtil = new SeekerUtil();
    }

    private SeekerUtil(){

    }

    public static SeekerUtil getInstance(){
        return seekerUtil;
    }

    public SeekerFormBean populateSeekerFormBean(HttpServletRequest req) {

        SeekerFormBean seekerFormBean = new SeekerFormBean();
        seekerFormBean.setMemberId(req.getParameter("memberId"));
        seekerFormBean.setFirstname(req.getParameter("firstname"));
        seekerFormBean.setLastname(req.getParameter("lastname"));
        seekerFormBean.setPhonenumber(req.getParameter("phonenumber"));
        seekerFormBean.setEmail(req.getParameter("email"));
        seekerFormBean.setAddress(req.getParameter("address"));
        seekerFormBean.setPassword(req.getParameter("password"));
        seekerFormBean.setPassword2(req.getParameter("password2"));
        seekerFormBean.setSpouseName(req.getParameter("spousename"));
        seekerFormBean.setNumberOfChildren(req.getParameter("numberofchildren"));
        seekerFormBean.setType(Type.SEEKER.name());
        req.setAttribute("SeekerFormBean",seekerFormBean);
        return seekerFormBean;
    }

    public SeekerModel populateSeekerModel(HttpServletRequest req) {
        SeekerUtil seekerUtil = SeekerUtil.getInstance();
        SeekerFormBean seekerFormBean = populateSeekerFormBean(req);
        //SeekerFormBean seekerFormBean = (SeekerFormBean) req.getAttribute("SeekerFormBean");
        SeekerModel seekerModel = new SeekerModel();
        int numberOfChildren;
        long phoneNumber = Long.parseLong(seekerFormBean.getPhonenumber());

        String passwordHash = PasswordHashHelper.get_SHA_256_SecurePassword(seekerFormBean.getPassword());
        try {
            numberOfChildren = Integer.parseInt(seekerFormBean.getNumberOfChildren());
        }catch(NumberFormatException e){
            numberOfChildren = 0;
        }

        seekerModel.setFirstName(seekerFormBean.getFirstname());
        seekerModel.setLastName(seekerFormBean.getLastname());
        seekerModel.setPhoneNumber(phoneNumber);
        seekerModel.setEmail(seekerFormBean.getEmail());
        seekerModel.setType(Type.SEEKER);
        seekerModel.setAddress(seekerFormBean.getAddress());
        seekerModel.setPassword(passwordHash);
        seekerModel.setNumberOfChildren(numberOfChildren);
        seekerModel.setSpouseName(seekerFormBean.getSpouseName());

        req.setAttribute("SeekerModel",seekerModel);
        return seekerModel;
    }

    public SeekerModel populateSeekerModelFromRequest(HttpServletRequest request){
        SeekerModel seekerModel = new SeekerModel();
        seekerModel.setMemberId(Integer.parseInt(request.getParameter("memberId")));
        seekerModel.setFirstName(request.getParameter("firstname"));
        seekerModel.setLastName(request.getParameter("lastname"));
        seekerModel.setPhoneNumber(Long.parseLong(request.getParameter("phonenumber")));
        seekerModel.setEmail(request.getParameter("email"));
        seekerModel.setType(Type.SEEKER);
        seekerModel.setAddress(request.getParameter("address"));
        seekerModel.setNumberOfChildren(Integer.parseInt(request.getParameter("numberofchildren")));
        seekerModel.setSpouseName(request.getParameter("spousename"));
        seekerModel.setPassword(request.getParameter("password"));
        return seekerModel;
    }

    public void populateSeekerFormBeanBySeekerModel(MemberModel memberModel, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        SeekerService seekerService = SeekerService.getInstance();
        SeekerFormBean seekerFormBean = new SeekerFormBean();

        seekerFormBean.setFirstname(memberModel.getFirstName());
        seekerFormBean.setLastname(memberModel.getLastName());
        seekerFormBean.setPhonenumber(String.valueOf(memberModel.getPhoneNumber()));
        seekerFormBean.setEmail(memberModel.getEmail());
        seekerFormBean.setAddress(memberModel.getAddress());
        seekerFormBean.setPassword(memberModel.getPassword());

        SeekerModel seekerModel = seekerService.getSeeker(memberModel.getMemberId());

        seekerFormBean.setSpouseName(seekerModel.getSpouseName());
        seekerFormBean.setNumberOfChildren(String.valueOf(seekerModel.getNumberOfChildren()));

        request.setAttribute("SeekerFormBean",seekerFormBean);
    }

}
