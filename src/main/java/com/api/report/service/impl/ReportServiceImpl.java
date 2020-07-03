package com.api.report.service.impl;

import com.api.report.bean.Report;
import com.api.report.dao.ReportDao;
import com.api.report.dao.impl.ReportDaoImpl;
import com.api.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDao reportDao;

    @Override
    public List<Object> getReports() {
        List<Object> reportList = null;
        try {
            reportList = reportDao.getReports();
        } catch (Exception e) {
            System.out.println("error when getting report" + e);
        }
        return reportList;
    }

    @Override
    public Map<String, String> getReportById(String reportId) {
        Map<String, String> resultMap = null;
        try {
            resultMap = reportDao.getReportById(reportId);
        } catch (Exception e) {
            System.out.println("error when getting report" + e);
        }
        return resultMap;
    }

    @Override
    public Map<String, String> addReport(Map<String, String> map) {
        Map<String, String> resultMap = null;
        try {
            resultMap = reportDao.addReport(map);
        } catch (Exception e) {
            System.out.println("error when add report" + e);
        }
        return resultMap;
    }


}
