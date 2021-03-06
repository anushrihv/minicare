package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import com.minicare.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseJob extends HttpServlet {
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
            MemberModel memberModel = (MemberModel) req.getSession().getAttribute("CurrentUser");
            List<JobModel> jobModelList = jobService.closeJob(jobId,memberModel);
            req.setAttribute("JobList",jobModelList);
            getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req,resp);
        }catch (Exception e){
            Logger logger = Logger.getLogger("CloseJob");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
