package com.minicare.controller.visitor;

import com.minicare.Exception.MiniCareException;
import com.minicare.dto.PasswordHashHelper;
import com.minicare.dto.SeekerFormBean;
import com.minicare.dto.SitterFormBean;
import com.minicare.model.SeekerModel;
import com.minicare.model.SitterModel;
import com.minicare.service.VisitorService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        action(req, resp);
    }




    private void action(HttpServletRequest req, HttpServletResponse resp){
        try {
            SitterFormBean sitterFormBean;
            SeekerFormBean seekerFormBean;
            SitterModel sitterModel;
            SeekerModel seekerModel;
            VisitorService visitorService = VisitorService.getInstance();
            String type = req.getParameter("type");


            if (type.equals("Sitter")) {
                visitorService.populateSitterFormBean(req);
                sitterFormBean = (SitterFormBean) req.getAttribute("SitterFormBean");

                if (!sitterFormBean.validate(req)) {
                    getServletContext().getRequestDispatcher("/jsp/sitter_register.jsp").forward(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    visitorService.storeSitterDetails(req);
                    session.setAttribute("CurrentUser",(SitterModel)req.getAttribute("SitterModel"));
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/sitter/homepage.do");

                }
            } else {
                visitorService.populateSeekerFormBean(req);
                seekerFormBean = (SeekerFormBean) req.getAttribute("SeekerFormBean");

                if (!seekerFormBean.validate(req)) {
                    getServletContext().getRequestDispatcher("/jsp/seeker_register.jsp").forward(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    visitorService.storeSeekerDetails(req);
                    session.setAttribute("CurrentUser",(SeekerModel)req.getAttribute("SeekerModel"));
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/seeker/homepage.do");
                }
            }
        }catch(Exception e){
            String message = e.getMessage();
            throw new MiniCareException(message);
        }
    }
}