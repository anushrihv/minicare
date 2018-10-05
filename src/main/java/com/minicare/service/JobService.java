package com.minicare.service;

import com.minicare.dao.JobApplicationDao;
import com.minicare.dao.JobDao;
import com.minicare.dto.JobFormBean;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
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

    public void storeJob(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        MemberModel memberModel = (MemberModel) request.getSession(false).getAttribute("CurrentUser");
        populateJobModel(request);
        JobModel jobModel = (JobModel) request.getAttribute("JobModel");
        JobDao jobDao = JobDao.getInstance();
        jobDao.storeJob(jobModel,memberModel);
    }

    private void populateJobModel(HttpServletRequest request){
        JobModel jobModel = new JobModel();
        JobFormBean jobFormBean = (JobFormBean) request.getAttribute("JobFormBean");
        Timestamp startDateTime = Timestamp.valueOf(jobFormBean.getStartDateTime());
        Timestamp endDateTime = Timestamp.valueOf(jobFormBean.getEndDateTime());
        double payPerHour = Double.parseDouble(jobFormBean.getPayPerHour());

        jobModel.setJobTitle(jobFormBean.getJobTitle());
        jobModel.setStartDateTime(startDateTime);
        jobModel.setEndDateTime(endDateTime);
        jobModel.setPayPerHour(payPerHour);

        request.setAttribute("JobModel",jobModel);
    }

    public List<JobModel> closeJob(int jobId,MemberModel memberModel) throws SQLException,ClassNotFoundException {
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        JobDao jobDao = JobDao.getInstance();

        jobApplicationDao.closeJobApplicationByJobId(jobId);
        jobDao.closeJob(jobId);
        List<JobModel> jobModelList = jobDao.getJobsById(memberModel);
        return jobModelList;
    }

    public JobModel getJobByJobId(int jobId) throws SQLException,ClassNotFoundException{
        JobDao jobDao = JobDao.getInstance();
        JobModel jobModel = jobDao.getJobByJobId(jobId);
        return jobModel;
    }

    public void populateJobFormBean(HttpServletRequest request)  {
        MemberModel memberModel = (MemberModel) request.getSession().getAttribute("CurrentUser");
        JobFormBean jobFormBean = new JobFormBean();
        jobFormBean.setId(request.getParameter("jobid"));
        jobFormBean.setJobTitle(request.getParameter("jobtitle"));
        jobFormBean.setStartDate(request.getParameter("startdate"));
        jobFormBean.setStartTime(request.getParameter("starttime"));
        jobFormBean.setEndDate(request.getParameter("enddate"));
        jobFormBean.setEndTime(request.getParameter("endtime"));
        jobFormBean.setStartDateTime();
        jobFormBean.setEndDateTime();
        jobFormBean.setPayPerHour(request.getParameter("payperhour"));
        jobFormBean.setPostedBy(String.valueOf(memberModel.getMemberId()));
        request.setAttribute("JobFormBean",jobFormBean);
    }

    public JobFormBean populateJobFormFromModel(JobModel jobModel){
        JobFormBean jobFormBean = new JobFormBean();
        jobFormBean.setId(String.valueOf(jobModel.getId()));
        jobFormBean.setJobTitle(jobModel.getJobTitle());
        jobFormBean.setStartDate(jobModel.getStartDateTime().toString().split(" ")[0]);
        jobFormBean.setStartTime(jobModel.getStartDateTime().toString().split(" ")[1].substring(0,5));
        jobFormBean.setEndDate(jobModel.getEndDateTime().toString().split(" ")[0]);
        jobFormBean.setEndTime(jobModel.getEndDateTime().toString().split(" ")[1].substring(0,5));
        jobFormBean.setStartDateTime();
        jobFormBean.setEndDateTime();
        jobFormBean.setPayPerHour(String.valueOf(jobModel.getPayPerHour()));
        return jobFormBean;
    }

    public void updateJob(JobFormBean jobFormBean) throws ClassNotFoundException, SQLException {
        JobModel jobModel = new JobModel();
        JobDao jobDao = JobDao.getInstance();

        jobModel.setId(Integer.parseInt(jobFormBean.getId()));
        jobModel.setJobTitle(jobFormBean.getJobTitle());
        jobModel.setStartDateTime(Timestamp.valueOf(jobFormBean.getStartDateTime()));
        jobModel.setEndDateTime(Timestamp.valueOf(jobFormBean.getEndDateTime()));
        jobModel.setPayPerHour(Double.parseDouble(jobFormBean.getPayPerHour()));
        jobDao.updateJob(jobModel);
    }

    public List<JobModel> getJobs(MemberModel memberModel) throws ClassNotFoundException,SQLException{
        JobDao jobDao = JobDao.getInstance();
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<JobModel> jobModelList = jobDao.getJobs();
        Iterator<JobModel> iterator = jobModelList.iterator();
        while(iterator.hasNext()){
            JobModel jobModel = iterator.next();
            int jobId = jobModel.getId();
            if(jobApplicationDao.getJobApplication(jobId,memberModel.getMemberId())!=null){
                iterator.remove();
            }
        }
        return jobModelList;
    }

    public void deleteJobsBySeeker(int seekerId) throws ClassNotFoundException,SQLException{
        JobDao jobDao = JobDao.getInstance();
        jobDao.closeJobByMemberId(seekerId);
    }

    public List<JobModel> getJobsById(MemberModel memberModel) throws ClassNotFoundException,SQLException{
        JobDao jobDao = JobDao.getInstance();

        return jobDao.getJobsById(memberModel);
    }
}
