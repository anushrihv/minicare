package com.minicare.controller.visitor;

import com.minicare.Exception.MiniCareException;
import com.minicare.dto.LoginFormBean;
import com.minicare.service.VisitorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req , HttpServletResponse resp)  {
        try {
            VisitorService visitorService = VisitorService.getInstance();
            visitorService.populateLoginForm(req);
            LoginFormBean loginFormBean = (LoginFormBean) req.getAttribute("LoginFormBean");
            if (!loginFormBean.validate(req)) {
                getServletContext().getRequestDispatcher("/jsp/welcome.jsp").forward(req, resp);
            } else if (!visitorService.authenticate(req, loginFormBean)) {
                getServletContext().getRequestDispatcher("/jsp/welcome.jsp").forward(req,resp);
            }else{
                PrintWriter out = resp.getWriter();
                out.print("authenticated");
                String type= loginFormBean.getType();
                if(type.equals("SITTER"))
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/sitter/homepage.do");
                else
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/seeker/homepage.do");
            }
        }catch (Exception e){
            throw new MiniCareException(e.getMessage());
        }
    }
}
