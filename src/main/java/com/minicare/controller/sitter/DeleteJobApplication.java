package com.minicare.controller.sitter;

import com.minicare.Exception.MiniCareException;
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

public class DeleteJobApplication extends HttpServlet {
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
            int jobApplicationId = Integer.parseInt(req.getParameter("JobApplicationId"));
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            jobApplicationService.deleteJobApplication(jobApplicationId);
            List<JobApplicationDTO> jobApplicationDTOList = jobApplicationService.getJobApplicationList(req);
            req.setAttribute("ActiveJobApplicationList",jobApplicationDTOList);
            getServletContext().getRequestDispatcher("/jsp/listActiveJobApplications.jsp").forward(req,resp);
            //resp.sendRedirect("/minicare-1.0-SNAPSHOT/jsp/listActiveJobApplications.jsp");
        }catch (Exception e){
            Logger logger = Logger.getLogger("DeleteJobApplication");
            logger.log(Level.SEVERE,"Exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}