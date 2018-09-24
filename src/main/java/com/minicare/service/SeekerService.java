package com.minicare.service;

import com.minicare.Exception.MiniCareException;
import com.minicare.dao.JobDao;
import com.minicare.dto.JobFormBean;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class SeekerService {
    static SeekerService seekerService;
    private JobFormBean jobFormBean;
    private JobModel jobModel;
    private JobDao jobDao;

    static{
        seekerService = new SeekerService();
    }

    private SeekerService(){

    }

    public static SeekerService getInstance(){
        return seekerService;
    }

    public void populateJobFormBean(HttpServletRequest request){
        jobFormBean = new JobFormBean();
        jobFormBean.setJobTitle(request.getParameter("jobtitle"));
        jobFormBean.setStartDateTime(request.getParameter("startdatetime"));
        jobFormBean.setEndDateTime(request.getParameter("enddatetime"));
        jobFormBean.setPayPerHour(request.getParameter("payperhour"));
        request.setAttribute("JobFormBean",jobFormBean);
    }

    public void storeJob(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        if(session==null) throw new MiniCareException("Session Expired!");
        MemberModel memberModel = (MemberModel) session.getAttribute("CurrentUser");
        populateJobModel(request);
        jobDao = JobDao.getInstance();
        jobDao.storeJob(jobModel,memberModel);
    }

    private void populateJobModel(HttpServletRequest request){
        jobModel = new JobModel();
        Timestamp startDateTime = Timestamp.valueOf(jobFormBean.getStartDateTime());
        Timestamp endDateTime = Timestamp.valueOf(jobFormBean.getEndDateTime());
        int payPerHour = Integer.parseInt(jobFormBean.getPayPerHour());

        jobModel.setJobTitle(jobFormBean.getJobTitle());
        jobModel.setStartDateTime(startDateTime);
        jobModel.setEndDateTime(endDateTime);
        jobModel.setPayPerHour(payPerHour);

        request.setAttribute("JobFormModel",jobModel);
    }

    public List<JobModel> getJobsById(HttpServletRequest request) throws SQLException,ClassNotFoundException{
        HttpSession session = request.getSession();
        jobDao = JobDao.getInstance();
        MemberModel memberModel = (MemberModel)session.getAttribute("CurrentUser");
        List<JobModel> jobModelList= jobDao.getJobsById(memberModel);
        return jobModelList;
    }
}
