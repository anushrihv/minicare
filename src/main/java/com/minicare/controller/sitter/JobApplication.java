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

public class JobApplication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        try {
            int id = (Integer)req.getSession(false).getAttribute("JobId");
            double expectedPay = Double.parseDouble(req.getParameter("expectedpay"));
            JobApplicationService jobApplicationService = JobApplicationService.getInstance();
            List<JobApplicationDTO> jobApplicationList = jobApplicationService.storeJobApplication(req,id,expectedPay);
            req.setAttribute("JobApplicationList",jobApplicationList);
            getServletContext().getRequestDispatcher("/jsp/listJobApplications.jsp").forward(req,resp);
        }catch (NumberFormatException n){
            req.setAttribute("ExpectedPayError","invalid input");
            getServletContext().getRequestDispatcher("/jsp/applyJob.jsp").forward(req,resp);
        }catch (Exception e){
            Logger logger = Logger.getLogger("ApplyJob");
            logger.log(Level.SEVERE,"Exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
