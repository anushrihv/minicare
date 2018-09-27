package com.minicare.service;

import com.minicare.dao.JobApplicationDao;
import com.minicare.dao.JobDao;
import com.minicare.dto.JobFormBean;
import com.minicare.model.JobModel;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class JobService {
    private static JobService jobService;

    private JobService(){

    }

    static{
        jobService = new JobService();
    }

    public static JobService getInstance(){
        return jobService;
    }

    public List<JobModel> closeJob(int jobId) throws SQLException,ClassNotFoundException {
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        JobDao jobDao = JobDao.getInstance();

        jobApplicationDao.closeJobApplicationByJobId(jobId);
        jobDao.closeJob(jobId);
        List<JobModel> jobModelList = jobDao.getJobs();
        return jobModelList;
    }

    public JobModel getJobByJobId(int jobId) throws SQLException,ClassNotFoundException{
        JobDao jobDao = JobDao.getInstance();
        JobModel jobModel = jobDao.getJobByJobId(jobId);
        return jobModel;
    }

    public void populateJobFormBean(HttpServletRequest request){
        JobFormBean jobFormBean = new JobFormBean();
        jobFormBean.setId(request.getParameter("jobid"));
        jobFormBean.setJobTitle(request.getParameter("jobtitle"));
        jobFormBean.setStartDateTime(request.getParameter("startdatetime"));
        jobFormBean.setEndDateTime(request.getParameter("enddatetime"));
        jobFormBean.setPayPerHour(request.getParameter("payperhour"));

        request.setAttribute("JobFormBean",jobFormBean);
    }

    public void updateJob(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        JobModel jobModel = new JobModel();
        JobDao jobDao = JobDao.getInstance();

        jobModel.setId(Integer.parseInt(request.getParameter("jobid")));
        jobModel.setJobTitle(request.getParameter("jobtitle"));
        jobModel.setStartDateTime(Timestamp.valueOf(request.getParameter("startdatetime")));
        jobModel.setEndDateTime(Timestamp.valueOf(request.getParameter("enddatetime")));
        jobModel.setPayPerHour(Double.parseDouble(request.getParameter("payperhour")));
        jobDao.updateJob(jobModel);
    }

    public List<JobModel> getJobs() throws ClassNotFoundException,SQLException{
        JobDao jobDao = JobDao.getInstance();
        return jobDao.getJobs();
    }

    public void deleteJobsBySeeker(int seekerId) throws ClassNotFoundException,SQLException{
        JobDao jobDao = JobDao.getInstance();
        jobDao.closeJobByMemberId(seekerId);
    }
}
