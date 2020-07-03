package com.api.report.service;

import com.api.report.bean.Report;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    List<Object> getReports() throws Exception;
    Map<String, String> getReportById(String reportId) throws Exception;
    Map<String, String> addReport(Map<String, String> map);
}
