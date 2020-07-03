package com.api.report.controller;

import com.api.report.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/getReports", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Object> getReports() {
        List<Object> reportList= null;
        try {
            reportList = reportService.getReports();
        } catch (Exception e) {
            System.out.println("error when get Report: " + e);
        }
        return reportList;
    }

    @GetMapping(value = "/getReport", produces = "application/json;charset=UTF-8")
    @ResponseBody
      public Map<String, String> getReportById(@RequestParam(name = "reportId", required = true) String reportId) {
        Map<String, String> resultMap = null;
        try {
            resultMap = reportService.getReportById(reportId);
        } catch (Exception e) {
            System.out.println("error when get Report: " + e);
        }
        return resultMap;
    }

    @RequestMapping(value = "/addReport", method = RequestMethod.POST, consumes = {"application/json"}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, String> addReport(@RequestBody Map<String, String> requestBody) {
        Map<String, String> resultMap = null;
        try {
            System.out.println(requestBody);
            resultMap = reportService.addReport(requestBody);
        } catch (Exception e) {
            System.out.println("error when add report: " + e);
        }
        return resultMap;
    }
}


