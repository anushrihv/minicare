package com.minicare.service;

import com.minicare.controller.seeker.SeekerUtil;
import com.minicare.controller.sitter.SitterUtil;
import com.minicare.controller.visitor.VisitorUtil;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dao.SitterDao;
import com.minicare.dto.*;
import com.minicare.model.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class VisitorService{
    private static VisitorService visitorService;
    private SeekerFormBean seekerFormBean;
    private SitterFormBean sitterFormBean;
    private SitterModel sitterModel;
    private SeekerModel seekerModel;
    private SeekerDao seekerDao;
    private SitterDao sitterDao;
    private MemberDao memberDao;
    private MemberModel memberModel;


    static {
        visitorService = new VisitorService();
    }

    private VisitorService(){

    }

    public static VisitorService getInstance(){
        return visitorService;
    }


    public void storeSitterDetails(HttpServletRequest req,HttpSession session) throws SQLException,ClassNotFoundException,NamingException{
        VisitorUtil visitorUtil = VisitorUtil.getInstance();
        SitterUtil sitterUtil = SitterUtil.getInstance();
        SitterModel sitterModel = sitterUtil.populateSitterModel(req);
        //SitterModel sitterModel = (SitterModel) req.getAttribute("SitterModel");
        sitterDao = SitterDao.getInstance();
        sitterDao.insertSitter(sitterModel);
        visitorUtil.populateModelFromDb(sitterModel.getEmail(),session);
    }

    public void storeSeekerDetails(HttpServletRequest req,HttpSession session) throws SQLException,ClassNotFoundException, NamingException {
        VisitorUtil visitorUtil = VisitorUtil.getInstance();
        SeekerUtil seekerUtil = SeekerUtil.getInstance();
        SeekerModel seekerModel = seekerUtil.populateSeekerModel(req);
        //SeekerModel seekerModel = (SeekerModel) req.getAttribute("SeekerModel");
        seekerDao = SeekerDao.getInstance();
        seekerDao.insertSeeker(seekerModel);
        visitorUtil.populateModelFromDb(seekerModel.getEmail(),session);
    }


    public boolean authenticate(HttpServletRequest req , LoginFormBean loginFormBean) throws SQLException,NamingException{
        boolean status=true;
        memberDao = MemberDao.getInstance();
        Set<MemberModel> memberModelSet = memberDao.getMember(loginFormBean.getEmail());
        Iterator<MemberModel> iterator = memberModelSet.iterator();
        if(!iterator.hasNext()){
            req.setAttribute("LoginEmailError","Email or Password is incorrect");
            status=false;
        }else{
            MemberModel memberModel = iterator.next();
            String memberStatus = memberModel.getStatus().name();
            if(memberStatus.equals("INACTIVE")){
                req.setAttribute("LoginEmailError","Email or Password is incorrect");
                return false;
            }
            String dbPassword = memberModel.getPassword();
            String userPasswordHash = PasswordHashHelper.get_SHA_256_SecurePassword(req.getParameter("loginpassword"));
            if(!userPasswordHash.equals(dbPassword)){
                req.setAttribute("LoginPasswordError","Incorrect Password");
                status=false;
            }
            loginFormBean.setType(memberModel.getType().name());
        }

        return status;
    }




}
