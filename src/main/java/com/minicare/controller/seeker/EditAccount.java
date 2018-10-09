package com.minicare.controller.seeker;

import com.minicare.exception.MiniCareException;
import com.minicare.dto.SeekerFormBean;
import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
import com.minicare.service.SeekerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        try{
            HttpSession session = req.getSession(false);
            MemberModel memberModel = (MemberModel) session.getAttribute("CurrentUser");
            SeekerService seekerService = SeekerService.getInstance();
            SeekerUtil seekerUtil = SeekerUtil.getInstance();
            SeekerModel seekerModel = seekerService.getSeeker(memberModel.getMemberId());

            req.setAttribute("SeekerModel",seekerModel);
            SeekerFormBean seekerFormBean = seekerUtil.populateSeekerFormBean(req);
            //SeekerFormBean seekerFormBean = (SeekerFormBean) req.getAttribute("SeekerFormBean");
            if(!seekerFormBean.validate(req)){
                getServletContext().getRequestDispatcher("/jsp/editSeekerAccount.jsp").forward(req,resp);
            }else{
                seekerModel = seekerService.editSeekerAccount(req);
                session.setAttribute("CurrentUser",seekerModel);
                req.setAttribute("HomePageMessage","Account Successfully edited");
                getServletContext().getRequestDispatcher("/jsp/seeker_homepage.jsp").forward(req,resp);
            }

        }catch (Exception e){
            Logger logger = Logger.getLogger("EditAccount");
            logger.log(Level.SEVERE,"exception occurred",e);
            throw new MiniCareException(e);
        }
    }
}
