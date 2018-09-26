package com.minicare.service;

import com.minicare.dao.JobApplicationDao;
import com.minicare.dto.JobApplicationDTO;
import com.minicare.model.JobApplicationModel;
import com.minicare.model.MemberModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class JobApplicationService {
    static JobApplicationService jobApplicationService;

    static {
        jobApplicationService = new JobApplicationService();
    }

    private JobApplicationService(){

    }

    public static JobApplicationService getInstance(){
        return jobApplicationService;
    }

    public List<JobApplicationDTO> storeJobApplication(HttpServletRequest request , int jobId , double expectedPay) throws SQLException,ClassNotFoundException {
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();

        populateJobApplicationModel(request,jobId,expectedPay);
        JobApplicationModel jobApplicationModel = (JobApplicationModel) request.getAttribute("JobApplicationModel");
        jobApplicationDao.storeJobApplication(jobApplicationModel);
        List<JobApplicationDTO> jobApplicationDTOList = getJobApplicationList(request);
        return jobApplicationDTOList;
    }

    private void populateJobApplicationModel(HttpServletRequest request , int jobId , double expectedPay){
        MemberModel memberModel = (MemberModel) request.getSession().getAttribute("CurrentUser");

        JobApplicationModel jobApplicationModel = new JobApplicationModel();
        jobApplicationModel.setJobId(jobId);
        jobApplicationModel.setMemberId(memberModel.getMemberId());
        jobApplicationModel.setExpectedPay(expectedPay);

        request.setAttribute("JobApplicationModel",jobApplicationModel);
    }

    public List<JobApplicationDTO> getJobApplicationList(HttpServletRequest request) throws SQLException,ClassNotFoundException{
        MemberModel memberModel = (MemberModel) request.getSession().getAttribute("CurrentUser");
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<JobApplicationDTO> jobApplicationDTOList = jobApplicationDao.getJobApplicationList(memberModel.getMemberId());
        return jobApplicationDTOList;
    }

    public void deleteJobApplication(int jobId, int memberId) throws SQLException,ClassNotFoundException{
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        jobApplicationDao.deleteJobApplication(jobId,memberId);
    }

    public List<JobApplicationDTO> getJobApplicationsByJobId(int jobId) throws ClassNotFoundException,SQLException{
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        List<JobApplicationDTO> jobApplicationDTOList = jobApplicationDao.getJobApplicationsByJobId(jobId);
        return jobApplicationDTOList;
    }

    public void closeSitterJobApplications(int sitterId) throws ClassNotFoundException,SQLException{
        JobApplicationDao jobApplicationDao = JobApplicationDao.getInstance();
        jobApplicationDao.closeJobApplicationsByMemberId(sitterId);
    }

}
