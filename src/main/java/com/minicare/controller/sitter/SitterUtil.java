package com.minicare.controller.sitter;

import com.minicare.dto.PasswordHashHelper;
import com.minicare.dto.SitterFormBean;
import com.minicare.model.MemberModel;
import com.minicare.model.SitterModel;
import com.minicare.model.Type;
import com.minicare.service.SitterService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SitterUtil {
    public static SitterUtil sitterUtil;

    private SitterUtil(){

    }

    static {
        sitterUtil = new SitterUtil();
    }

    public static SitterUtil getInstance(){
        return sitterUtil;
    }

    public SitterFormBean populateSitterFormBean(HttpServletRequest req)  {

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
        return sitterFormBean;
    }

    public SitterModel populateSitterModel(HttpServletRequest req) {
        SitterFormBean sitterFormBean = populateSitterFormBean(req);
        //SitterFormBean sitterFormBean = (SitterFormBean) req.getAttribute("SitterFormBean");
        SitterModel sitterModel = new SitterModel();
        long phoneNumber = Long.parseLong(sitterFormBean.getPhonenumber());
        int yearsOfExperience = Integer.parseInt(sitterFormBean.getYearsOfExperience());
        int expectedPay = Integer.parseInt(sitterFormBean.getExpectedPay());
        String passwordHash = PasswordHashHelper.get_SHA_256_SecurePassword(sitterFormBean.getPassword());

        sitterModel.setFirstName(sitterFormBean.getFirstname());
        sitterModel.setLastName(sitterFormBean.getLastname());
        sitterModel.setPhoneNumber(phoneNumber);
        sitterModel.setEmail(sitterFormBean.getEmail());
        sitterModel.setType(Type.SITTER);
        sitterModel.setAddress(sitterFormBean.getAddress());
        sitterModel.setPassword(passwordHash);
        sitterModel.setYearsOfExperience(yearsOfExperience);
        sitterModel.setExpectedPay(expectedPay);

        req.setAttribute("SitterModel",sitterModel);
        return sitterModel;

    }

    public SitterModel populateSitterModelFromRequest(HttpServletRequest request){
        SitterModel sitterModel = new SitterModel();
        sitterModel.setMemberId(Integer.parseInt(request.getParameter("memberId")));
        sitterModel.setFirstName(request.getParameter("firstname"));
        sitterModel.setLastName(request.getParameter("lastname"));
        sitterModel.setPhoneNumber(Long.parseLong(request.getParameter("phonenumber")));
        sitterModel.setEmail(request.getParameter("email"));
        sitterModel.setType(Type.SITTER);
        sitterModel.setAddress(request.getParameter("address"));
        sitterModel.setYearsOfExperience(Integer.parseInt(request.getParameter("yearsofexperience")));
        sitterModel.setExpectedPay(Double.parseDouble(request.getParameter("expectedpay")));
        sitterModel.setPassword(request.getParameter("password"));
        return sitterModel;
    }

    public void populateSitterFormBeanBySitterModel(MemberModel memberModel, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        SitterService sitterService = SitterService.getInstance();
        SitterFormBean sitterFormBean = new SitterFormBean();

        sitterFormBean.setFirstname(memberModel.getFirstName());
        sitterFormBean.setLastname(memberModel.getLastName());
        sitterFormBean.setPhonenumber(String.valueOf(memberModel.getPhoneNumber()));
        sitterFormBean.setEmail(memberModel.getEmail());
        sitterFormBean.setAddress(memberModel.getAddress());
        sitterFormBean.setPassword(memberModel.getPassword());

        SitterModel sitterModel = sitterService.getSitter(memberModel.getMemberId());

        sitterFormBean.setYearsOfExperience(String.valueOf(sitterModel.getYearsOfExperience()));
        sitterFormBean.setExpectedPay(String.valueOf(sitterModel.getExpectedPay()));

        request.setAttribute("SitterFormBean",sitterFormBean);
    }
}
