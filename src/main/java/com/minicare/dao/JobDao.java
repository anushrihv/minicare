package com.minicare.dao;

import com.minicare.model.JobModel;
import com.minicare.model.MemberModel;
import com.minicare.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDao {
    static JobDao jobDao;
    PreparedStatement preparedStatement;

    static{
        jobDao = new JobDao();
    }

    private JobDao(){

    }

    public static JobDao getInstance(){
        return jobDao;
    }

    public void storeJob(JobModel jobModel, MemberModel memberModel) throws ClassNotFoundException, SQLException {
        Connection connection = JDBCHelper.getConnection();
        MemberDao memberDao = MemberDao.getInstance();
        String email = memberModel.getEmail();

        ResultSet resultSet = memberDao.getMember(email);
        resultSet.next();
        int id = resultSet.getInt("Id");

        String sql = "insert into job(Title,PostedBy,StartDateTime,EndDateTime,PayPerHour) values(?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,jobModel.getJobTitle());
        preparedStatement.setInt(2,id);
        preparedStatement.setTimestamp(3,jobModel.getStartDateTime());
        preparedStatement.setTimestamp(4,jobModel.getEndDateTime());
        preparedStatement.setInt(5,jobModel.getPayPerHour());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public List<JobModel> getJobsById(MemberModel memberModel) throws ClassNotFoundException,SQLException{
        Connection connection = JDBCHelper.getConnection();
        MemberDao memberDao = MemberDao.getInstance();
        String email = memberModel.getEmail();
        List<JobModel> jobModelList = new ArrayList<JobModel>();
        JobModel jobModel = new JobModel();

        ResultSet resultSet = memberDao.getMember(email);
        resultSet.next();
        int id = resultSet.getInt("Id");

        String sql = "select * from job where Status=? and PostedBy=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Status.ACTIVE.name());
        preparedStatement.setInt(2,id);
        resultSet = preparedStatement.executeQuery();
        while (true){
           boolean contains = resultSet.next();
           if(contains){
               jobModel.setJobTitle(resultSet.getString("Title"));
               jobModel.setStartDateTime(resultSet.getTimestamp("StartDateTime"));
               jobModel.setEndDateTime(resultSet.getTimestamp("EndDateTime"));
               jobModel.setPayPerHour(resultSet.getInt("PayPerHour"));
               jobModel.setStatus(Status.valueOf(resultSet.getString("Status")));
               jobModelList.add(jobModel);
           }else{
               break;
           }
        }
        return jobModelList;
    }


}
