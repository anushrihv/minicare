package com.minicare.dao;

import com.minicare.model.MemberModel;
import com.minicare.model.SeekerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

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

            //ResultSet resultSet = memberDao.getMember(seekerModel.getEmail());
            Set<MemberModel> memberModelSet = memberDao.getMember(seekerModel.getEmail());
            Iterator<MemberModel> iterator = memberModelSet.iterator();
            MemberModel memberModel = iterator.next();
            int id = memberModel.getMemberId();

            String sql = "insert into seeker(MemberId,NumberOfChildren,SpouseName) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, seekerModel.getNumberOfChildren());
            preparedStatement.setString(3, seekerModel.getSpouseName());
            preparedStatement.executeUpdate();

            try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
            try { JDBCHelper.closeConnection(); } catch (Exception e) { /* ignored */ }
    }
}
