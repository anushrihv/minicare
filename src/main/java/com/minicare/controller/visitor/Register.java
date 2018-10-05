package com.minicare.controller.visitor;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.SeekerFormBean;
import com.minicare.dto.SitterFormBean;
import com.minicare.service.MemberService;
import com.minicare.service.SeekerService;
import com.minicare.service.SitterService;
import com.minicare.service.VisitorService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            VisitorService visitorService = VisitorService.getInstance();
            SeekerService seekerService = SeekerService.getInstance();
            SitterService sitterService = SitterService.getInstance();
            String type = req.getParameter("type");


            if (type.equals("Sitter")) {
                sitterService.populateSitterFormBean(req);
                sitterFormBean = (SitterFormBean) req.getAttribute("SitterFormBean");
                MemberService memberService = MemberService.getInstance();

                if (!sitterFormBean.validate(req)) {
                    getServletContext().getRequestDispatcher("/jsp/sitter_register.jsp").forward(req, resp);
                } else if(!memberService.uniqueEmail(sitterFormBean.getEmail())){
                    req.setAttribute("EmailError","This email already exists");
                    getServletContext().getRequestDispatcher("/jsp/sitter_register.jsp").forward(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    visitorService.storeSitterDetails(req,session);
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/sitter/homepage.do");
                }
            } else {
                seekerService.populateSeekerFormBean(req);
                seekerFormBean = (SeekerFormBean) req.getAttribute("SeekerFormBean");
                MemberService memberService = MemberService.getInstance();

                if (!seekerFormBean.validate(req)) {
                    getServletContext().getRequestDispatcher("/jsp/seeker_register.jsp").forward(req, resp);
                } else if(!memberService.uniqueEmail(seekerFormBean.getEmail())){
                    req.setAttribute("EmailError","This email already exists");
                    getServletContext().getRequestDispatcher("/jsp/seeker_register.jsp").forward(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    visitorService.storeSeekerDetails(req,session);
                    resp.sendRedirect("/minicare-1.0-SNAPSHOT/seeker/homepage.do");
                }
            }
        }catch(Exception e){
            Logger logger = Logger.getLogger("Register");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}