package com.minicare.controller.seeker;

import com.minicare.Exception.MiniCareException;
import com.minicare.model.JobModel;
import com.minicare.service.SeekerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListJob extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        SeekerService seekerService = SeekerService.getInstance();
        try {
            List<JobModel> jobModelList = seekerService.getJobsById(req);
            req.setAttribute("JobList",jobModelList);
            getServletContext().getRequestDispatcher("/jsp/listJobs.jsp").forward(req, resp);
        }catch(Exception e){
            throw new MiniCareException(e.getMessage());
        }
    }
}
