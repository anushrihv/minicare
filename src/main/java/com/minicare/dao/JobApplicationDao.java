package com.minicare.dao;

import com.minicare.dto.JobApplicationDTO;
import com.minicare.model.JobApplicationModel;
import com.minicare.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDao {
    static JobApplicationDao jobApplicationDao;

    static {
        jobApplicationDao = new JobApplicationDao();
    }

    private JobApplicationDao(){

    }

    public static JobApplicationDao getInstance(){
        return jobApplicationDao;
    }

    public void storeJobApplication(JobApplicationModel jobApplicationModel) throws SQLException,ClassNotFoundException{
        Connection connection = JDBCHelper.getConnection();
        String sql = "insert into jobapplication(JobId,MemberId,ExpectedPay) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,jobApplicationModel.getJobId());
        preparedStatement.setInt(2,jobApplicationModel.getMemberId());
        preparedStatement.setDouble(3,jobApplicationModel.getExpectedPay());
        preparedStatement.executeUpdate();
    }

    public List<JobApplicationDTO> getJobApplicationList(int memberId) throws SQLException,ClassNotFoundException{
        List<JobApplicationDTO> jobApplicationDTOList = new ArrayList<JobApplicationDTO>();
        Connection connection = JDBCHelper.getConnection();
        String sql = "select ja.Id , ja.JobId , ja.MemberId , j.Title , ja.ExpectedPay , j.PayPerHour , ja.Status " +
                "from jobapplication as ja , job as j , member as m " +
                "where ja.JobId = j.Id and ja.MemberId = m.Id and ja.MemberId=? and ja.Status=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,memberId);
        preparedStatement.setString(2,Status.ACTIVE.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(true){
            boolean contains = resultSet.next();
            if(contains){
                JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
                jobApplicationDTO.setJobApplicationId(resultSet.getInt("Id"));
                jobApplicationDTO.setJobId(resultSet.getInt("JobId"));
                jobApplicationDTO.setMemberId(resultSet.getInt("MemberId"));
                jobApplicationDTO.setJobTitle(resultSet.getString("Title"));
                jobApplicationDTO.setExpectedPay(resultSet.getDouble("ExpectedPay"));
                jobApplicationDTO.setPayPerHour(resultSet.getDouble("PayPerHour"));
                jobApplicationDTO.setStatus(Status.valueOf(resultSet.getString("Status")));
                jobApplicationDTOList.add(jobApplicationDTO);
            }else{
                break;
            }
        }
        return jobApplicationDTOList;
    }

    public void deleteJobApplication(int jobApplicationId) throws SQLException,ClassNotFoundException{
        Connection connection = JDBCHelper.getConnection();
        String sql ="update jobapplication SET Status=? where Id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Status.INACTIVE.name());
        preparedStatement.setInt(2,jobApplicationId);
        preparedStatement.executeUpdate();
    }
}