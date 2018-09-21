package com.minicare.dao;

import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
    private static MemberDao memberDao;

    private MemberDao() {

    }

    static {
        memberDao = new MemberDao();
    }

    public static MemberDao getInstance() {
        return memberDao;
    }

    public void insertMember(Connection connection, MemberModel memberModel) throws SQLException{

            PreparedStatement preparedStatement;

            String sql = "insert into member(FirstName,LastName,PhoneNumber,EmailAddress,Type,Address,Password) values(?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberModel.getFirstName());
            preparedStatement.setString(2, memberModel.getLastName());
            preparedStatement.setLong(3, memberModel.getPhoneNumber());
            preparedStatement.setString(4, memberModel.getEmail());
            preparedStatement.setString(5, memberModel.getType().name());
            preparedStatement.setString(6, memberModel.getAddress());
            preparedStatement.setString(7, memberModel.getPassword());

            preparedStatement.executeUpdate();


    }

    public ResultSet getMember(String email) throws SQLException,ClassNotFoundException {
        Connection connection = JDBCHelper.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "select * from member where EmailAddress = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
