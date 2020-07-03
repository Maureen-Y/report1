package com.api.report.dao;

import com.api.report.bean.Report;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public interface ReportDao {
    List<Object> getReports();
    Map<String, String> getReportById(String reportId);
//    Map<String, String> addReport(Report report);

    Map<String, String> addReport(Map<String, String> map);
}
