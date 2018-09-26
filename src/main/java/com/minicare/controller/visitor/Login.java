package com.minicare.controller.visitor;

import com.minicare.Exception.MiniCareException;
import com.minicare.dto.LoginFormBean;
import com.minicare.service.VisitorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            }else if (!visitorService.authenticate(req, loginFormBean)) {
                getServletContext().getRequestDispatcher("/jsp/welcome.jsp").forward(req,resp);
            }else{
                String type= loginFormBean.getType();
                String email = loginFormBean.getEmail();
                HttpSession session = req.getSession();
                visitorService.populateModelFromDb(email,session);
                if(type.equals("SITTER")) {
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/sitter/homepage.do");
                }
                else
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/seeker/homepage.do");
            }
        }catch (Exception e){
            Logger logger = Logger.getLogger("Login");
            logger.log(Level.SEVERE,"Exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
