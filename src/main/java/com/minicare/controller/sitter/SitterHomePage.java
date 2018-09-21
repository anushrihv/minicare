package com.minicare.controller.sitter;

import com.minicare.Exception.MiniCareException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SitterHomePage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req , HttpServletResponse resp) {
        try {
            getServletContext().getRequestDispatcher("/jsp/sitter_homepage.jsp").forward(req, resp);
        }catch(Exception e){
            String message = e.getMessage();
            throw new MiniCareException(message);
        }
    }
}

