package com.minicare.service;

import com.minicare.dao.MemberDao;
import com.minicare.model.MemberModel;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class MemberService {
    private static MemberService memberService;

    private MemberService(){

    }

    static {
        memberService = new MemberService();
    }

    public static MemberService getInstance(){
        return memberService;
    }

    public  boolean uniqueEmail(String email) throws ClassNotFoundException, SQLException {
        MemberDao memberDao = MemberDao.getInstance();
        Set<MemberModel> memberModelSet = memberDao.getMember(email);
        Iterator<MemberModel> iterator = memberModelSet.iterator();
        if(iterator.hasNext()){
            return false;
        }
        else
            return true;
    }
}
