package com.minicare.controller.seeker;

import com.minicare.Exception.MiniCareException;
import com.minicare.dto.JobFormBean;
import com.minicare.model.JobModel;
import com.minicare.service.SeekerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

public class PostJob extends HttpServlet {
    private SeekerService seekerService;
    private JobFormBean jobFormBean;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req , HttpServletResponse resp){
        try {

            String url = String.valueOf(req.getRequestURL());
            if(url.contains("/seeker/postjobform.do")){
                getServletContext().getRequestDispatcher("/jsp/postJob.jsp").forward(req,resp);

            }else if(url.contains("/seeker/postjob.do")) {
                seekerService = SeekerService.getInstance();
                seekerService.populateJobFormBean(req);
                jobFormBean = (JobFormBean) req.getAttribute("JobFormBean");
                if (jobFormBean!=null && !jobFormBean.validate(req)) {
                    getServletContext().getRequestDispatcher("/jsp/postJob.jsp").forward(req, resp);
                }else{
                    seekerService.storeJob(req);
                    List<JobModel> jobModelList = seekerService.getJobsById(req);
                    req.setAttribute("JobList",jobModelList);
                    getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req,resp);
                }
            }
        }catch (Exception e){
            throw new MiniCareException(e.getMessage());
        }
    }
}
