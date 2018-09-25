package com.minicare.controller.sitter;

import com.minicare.Exception.MiniCareException;
import com.minicare.model.JobModel;
import com.minicare.service.SitterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowJob extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        SitterService sitterService = SitterService.getInstance();
        try{
            List<JobModel> jobModelList = sitterService.getJobs();
            req.setAttribute("JobList",jobModelList);
            getServletContext().getRequestDispatcher("/jsp/listActiveJobs.jsp").forward(req, resp);
        }catch (Exception e){
            throw new MiniCareException(e.getMessage());
        }
    }
}
