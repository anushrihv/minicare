package com.minicare.service;

import com.minicare.dao.JobDao;
import com.minicare.dao.MemberDao;
import com.minicare.dao.SeekerDao;
import com.minicare.dto.JobFormBean;
import com.minicare.dto.SeekerFormBean;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
import com.minicare.model.Type;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class SeekerService {
    static SeekerService seekerService;
    private JobDao jobDao;

    static{
        seekerService = new SeekerService();
    }

    private SeekerService(){

    }

    public static SeekerService getInstance(){
        return seekerService;
    }

    public List<JobModel> getJobsById(HttpServletRequest request) throws SQLException,ClassNotFoundException{
        HttpSession session = request.getSession(false);
        jobDao = JobDao.getInstance();
        MemberModel memberModel = (MemberModel)session.getAttribute("CurrentUser");
        List<JobModel> jobModelList= jobDao.getJobsById(memberModel);
        return jobModelList;
    }

    public void closeSeekerAccount(int seekerId) throws NamingException,SQLException{
        MemberDao memberDao = MemberDao.getInstance();
        memberDao.deleteMember(seekerId);
    }

    public void populateSeekerFormBean(HttpServletRequest req) {

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
    }

    public void populateSeekerFormBeanBySeekerModel(MemberModel memberModel,HttpServletRequest request) throws ClassNotFoundException,SQLException{
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

    public SeekerModel getSeeker(int seekerId) throws ClassNotFoundException,SQLException{
        SeekerDao seekerDao = SeekerDao.getInstance();
        return seekerDao.getSeeker(seekerId);
    }

    public SeekerModel populateSeekerModel(HttpServletRequest request){
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

    public SeekerModel editSeekerAccount(HttpServletRequest request) throws ClassNotFoundException,SQLException, NamingException {
        SeekerDao seekerDao = SeekerDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        SeekerModel seekerModel = populateSeekerModel(request);
        seekerDao.editSeeker(seekerModel);
        memberDao.editMember(seekerModel);
        return seekerModel;
    }
}
