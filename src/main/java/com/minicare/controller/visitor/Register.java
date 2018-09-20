package com.minicare.controller.visitor;

import com.minicare.dto.SeekerFormBean;
import com.minicare.dto.SitterFormBean;
import com.minicare.service.VisitorService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }




    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try {
                SitterFormBean sitterFormBean;
                SeekerFormBean seekerFormBean
                String type = req.getParameter("type");
                VisitorService visitorService = VisitorService.getInstance();
                if (type.equals("Sitter")) {
                    visitorService.populateSitterFormBean(req);
                    sitterFormBean = (SitterFormBean) req.getAttribute("SitterFormBean");

                    if(!sitterFormBean.validate(req)){
                        getServletContext().getRequestDispatcher("/jsp/sitter_register.jsp").forward(req,resp);
                    }else{

                    }
                } else {
                    visitorService.populateSeekerFormBean(req);
                    seekerFormBean = (SeekerFormBean) req.getAttribute("SeekerFormBean");

                    if (!seekerFormBean.validate(req)) {
                        getServletContext().getRequestDispatcher("/jsp/seeker_register.jsp").forward(req, resp);
                    }else{

                    }
                }

        }catch(ServletException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}