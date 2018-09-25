package com.minicare.service;

import com.minicare.dao.JobDao;
import com.minicare.model.JobModel;

import java.sql.SQLException;
import java.util.List;

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

    public List<JobModel> getJobs() throws SQLException,ClassNotFoundException {
        JobDao jobDao = JobDao.getInstance();
        List<JobModel> jobModelList = jobDao.getJobs();
        return jobModelList;
    }
}
