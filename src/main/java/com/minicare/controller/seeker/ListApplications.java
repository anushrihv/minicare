package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.JobApplicationDTO;
import com.minicare.service.JobApplicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListApplications extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int jobId = Integer.parseInt(req.getParameter("JobId"));
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            List<JobApplicationDTO> jobApplicationDTOList = jobApplicationService.getJobApplicationsByJobId(jobId);
            req.setAttribute("JobApplicationList",jobApplicationDTOList);
            getServletContext().getRequestDispatcher("/jsp/seekerJobApplications.jsp").forward(req,resp);
        }catch (Exception e){
            Logger logger = Logger.getLogger("ListApplications");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
