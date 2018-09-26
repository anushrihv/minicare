package com.minicare.dao;

import com.minicare.model.MemberModel;
import com.minicare.model.SitterModel;
import com.minicare.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class SitterDao{
    private static SitterDao sitterDao;

    private SitterDao(){

    }

    static{
        sitterDao = new SitterDao();
    }

    public static SitterDao getInstance(){
        return sitterDao;
    }

    public void insertSitter(SitterModel sitterModel) throws ClassNotFoundException, SQLException {
        Connection connection = JDBCHelper.getConnection();
        PreparedStatement preparedStatement;
        MemberDao memberDao = MemberDao.getInstance();
        memberDao.insertMember(connection,sitterModel);

        Set<MemberModel> memberModelSet = memberDao.getMember(sitterModel.getEmail());
        Iterator<MemberModel> iterator = memberModelSet.iterator();
        MemberModel memberModel = iterator.next();
        int id = memberModel.getMemberId();

        String sql = "insert into sitter(MemberId,YearsOfExperience,ExpectedPay) values (?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,sitterModel.getYearsOfExperience());
        preparedStatement.setDouble(3,sitterModel.getExpectedPay());
        preparedStatement.executeUpdate();

        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        try { connection.close(); } catch (Exception e) { /* ignored */ }
    }

    public void deleteSitter(int memberId) throws ClassNotFoundException, SQLException{
        Connection connection = JDBCHelper.getConnection();
        String sql ="update sitter SET Status=? where MemberId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Status.INACTIVE.name());
        preparedStatement.setInt(2,memberId);
        preparedStatement.executeUpdate();

        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        try { connection.close(); } catch (Exception e) { /* ignored */ }
    }
}
