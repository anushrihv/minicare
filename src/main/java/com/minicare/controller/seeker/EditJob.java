package com.minicare.controller.seeker;

import com.minicare.Exception.MiniCareException;
import com.minicare.model.JobModel;
import com.minicare.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditJob extends HttpServlet {
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
            JobService jobService = JobService.getInstance();
            JobModel jobModel = jobService.getJobByJobId(jobId);
            req.setAttribute("JobModel",jobModel);
            getServletContext().getRequestDispatcher("/jsp/editJob.jsp").forward(req,resp);
        }catch (Exception e){
            Logger logger = Logger.getLogger("EditJob");
            logger.log(Level.SEVERE,"Exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
