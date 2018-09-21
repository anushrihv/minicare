package com.minicare.dao;

import com.minicare.model.SitterModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        ResultSet resultSet = memberDao.getMember(sitterModel.getEmail());
        resultSet.next();
        int id = resultSet.getInt("Id");

        String sql = "insert into sitter(MemberId,YearsOfExperience,ExpectedPay) values (?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,sitterModel.getYearsOfExperience());
        preparedStatement.setDouble(3,sitterModel.getExpectedPay());
        preparedStatement.executeUpdate();

        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        try { JDBCHelper.closeConnection(); } catch (Exception e) { /* ignored */ }
    }
}
