package com.minicare.service;

import com.minicare.dao.MemberDao;
import com.minicare.dao.SitterDao;
import java.sql.SQLException;


public class SitterService {
    static SitterService sitterService;

    static {
        sitterService = new SitterService();
    }

    private SitterService(){

    }

    public static SitterService getInstance(){
        return sitterService;
    }

    public void closeSitterAccount(int memberId) throws ClassNotFoundException,SQLException{
        SitterDao sitterDao = SitterDao.getInstance();
        MemberDao memberDao = MemberDao.getInstance();

        sitterDao.deleteSitter(memberId);
        memberDao.deleteMember(memberId);
    }
}
