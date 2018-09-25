package com.minicare.controller.seeker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        int jobId = Integer.parseInt(req.getParameter("JobId"));
    }
}
