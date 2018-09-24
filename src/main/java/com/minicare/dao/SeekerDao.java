package com.minicare.dao;

import com.minicare.model.SeekerModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeekerDao {
    private static SeekerDao seekerDao;
    private PreparedStatement preparedStatement;

    private SeekerDao(){

    }

    static {
        seekerDao = new SeekerDao();
    }

    public static SeekerDao getInstance(){
        return seekerDao;
    }

    public void insertSeeker(SeekerModel seekerModel) throws ClassNotFoundException, SQLException {

            Connection connection = JDBCHelper.getConnection();


            MemberDao memberDao = MemberDao.getInstance();
            memberDao.insertMember(connection, seekerModel);

            ResultSet resultSet = memberDao.getMember(seekerModel.getEmail());
            resultSet.next();
            int id = resultSet.getInt("Id");

            String sql = "insert into seeker(MemberId,NumberOfChildren,SpouseName) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, seekerModel.getNumberOfChildren());
            preparedStatement.setString(3, seekerModel.getSpouseName());
            preparedStatement.executeUpdate();

            try { resultSet.close(); } catch (Exception e) { /* ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
            try { JDBCHelper.closeConnection(); } catch (Exception e) { /* ignored */ }
    }
}
