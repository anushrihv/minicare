package com.minicare.service;

import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.SeekerFormBean;
import com.minicare.dto.SitterFormBean;
import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
import com.minicare.model.SitterModel;
import com.minicare.model.Type;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class SitterService {
    static SitterService sitterService;

    static {
        sitterService = new SitterService();
    }

    private SitterService(){

    }

    public static SitterService getInstance(){
        return sitterService;
    }

    public void closeSitterAccount(int memberId) throws ClassNotFoundException,SQLException,NamingException{
        SitterDao sitterDao = SitterDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        sitterDao.deleteSitter(memberId);
        memberDao.deleteMember(memberId);
    }

    public void populateSitterFormBean(HttpServletRequest req)  {

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

    public void populateSitterFormBeanBySitterModel(MemberModel memberModel, HttpServletRequest request) throws ClassNotFoundException,SQLException{
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

    public SitterModel getSitter(int sitterId) throws ClassNotFoundException,SQLException{
        SitterDao sitterDao = SitterDao.getInstance();
        return sitterDao.getSitter(sitterId);
    }

    public SitterModel populateSitterModel(HttpServletRequest request){
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

    public SitterModel editSitterAccount(HttpServletRequest request) throws ClassNotFoundException,SQLException, NamingException {
        SitterDao sitterDao = SitterDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        SitterModel sitterModel = populateSitterModel(request);
        sitterDao.editSitter(sitterModel);
        memberDao.editMember(sitterModel);
        return sitterModel;
    }
}
