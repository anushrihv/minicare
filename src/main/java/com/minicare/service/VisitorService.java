package com.minicare.service;

import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.LoginFormBean;
import com.minicare.dto.PasswordHashHelper;
import com.minicare.dto.SeekerFormBean;
import com.minicare.dto.SitterFormBean;
import com.minicare.model.SeekerModel;
import com.minicare.model.SitterModel;
import com.minicare.model.Type;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorService{
    private static VisitorService visitorService;
    private SeekerFormBean seekerFormBean;
    private SitterFormBean sitterFormBean;
    private SitterModel sitterModel;
    private SeekerModel seekerModel;
    private SeekerDao seekerDao;
    private SitterDao sitterDao;
    private MemberDao memberDao;


    static {
        visitorService = new VisitorService();
    }

    private VisitorService(){

    }

    public static VisitorService getInstance(){
        return visitorService;
    }

    public void populateSeekerFormBean(HttpServletRequest req) throws NoSuchAlgorithmException{

        seekerFormBean = new SeekerFormBean();
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

    public void populateSitterFormBean(HttpServletRequest req) throws NoSuchAlgorithmException {

        sitterFormBean = new SitterFormBean();
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

    public void storeSitterDetails(HttpServletRequest req) throws SQLException,ClassNotFoundException,NoSuchAlgorithmException{
        populateSitterModel(req);
        SitterModel sitterModel = (SitterModel) req.getAttribute("SitterModel");
        sitterDao = SitterDao.getInstance();
        sitterDao.insertSitter(sitterModel);

    }

    public void storeSeekerDetails(HttpServletRequest req) throws SQLException,ClassNotFoundException,NoSuchAlgorithmException {
        populateSeekerModel(req);
        SeekerModel seekerModel = (SeekerModel) req.getAttribute("SeekerModel");
        seekerDao = SeekerDao.getInstance();
        seekerDao.insertSeeker(seekerModel);
    }

    private void populateSitterModel(HttpServletRequest req) throws NoSuchAlgorithmException{

        sitterModel = new SitterModel();
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
    }

    private void populateSeekerModel(HttpServletRequest req) throws NoSuchAlgorithmException{
        seekerModel = new SeekerModel();
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
    }

    public void populateLoginForm(HttpServletRequest request){
        LoginFormBean loginFormBean = new LoginFormBean();
        loginFormBean.setEmail(request.getParameter("loginemail"));
        loginFormBean.setPassword(request.getParameter("loginpassword"));
        request.setAttribute("LoginFormBean",loginFormBean);
    }

    public boolean authenticate(HttpServletRequest req , LoginFormBean loginFormBean) throws SQLException,ClassNotFoundException{
        boolean status=true;
        memberDao = MemberDao.getInstance();
        ResultSet resultSet = memberDao.getMember(loginFormBean.getEmail());
        if(resultSet.next()==false){
            req.setAttribute("LoginEmailError","This Email does not exist . Please register to continue");
            status=false;
        }else{
            String dbPassword = resultSet.getString("Password");
            String userPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(req.getParameter("loginpassword"));
            if(!userPasswordHash.equals(dbPassword)){
                req.setAttribute("LoginPasswordError","Incorrect Password");
                status=false;
            }
        }

        if(status==true){
            loginFormBean.setType(resultSet.getString("Type"));
        }

        return status;
    }
}
