package com.api.report.dao.impl;

import com.api.report.bean.Report;
import com.api.report.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class ReportDaoImpl implements ReportDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Object> getReports() {
        String query = null;

        query = "select * from test1";

        List<Object> list = new ArrayList<>();
//        try {
//            reports = jdbcTemplate.query(query, (rs, rownumber) -> {
//                Report r1 =new Report();
//                r1.setReportId(rs.getInt("id"));
//                r1.setName(rs.getString("name"));
//                System.out.println(r1);
//                return r1;
//            });
//
//        } catch (Exception e) {
//            System.out.println("sql exception for query: " + e);
//        }
//
//        try {
//            Object o = jdbcTemplate.query("SELECT TOP 1 * FROM TEST", (rs, rownumber) -> {
//                ResultSetMetaData rsmd = rs.getMetaData();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    System.out.println("test column label: " + rsmd.getColumnLabel(i));
//                }
//                return null;
//            });
//        } catch (Exception e) {
//            System.out.println("error when getting column label: " + e);
//        }

        try {
            List<Object> finalList = list;
            jdbcTemplate.query(query, (rs, rownumber) -> {
                Map<String, String> map = new HashMap<>();
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    int type = rsmd.getColumnType(i);
                    if (type == Types.VARCHAR) {
                        map.put(rsmd.getColumnLabel(i), rs.getString(i));
                    } else if (type == Types.INTEGER) {
                        map.put(rsmd.getColumnLabel(i), Integer.toString(rs.getInt(i)));
                    }
                }
                finalList.add(map);

                return finalList;
            });
            list = finalList;
        } catch (Exception e) {
            System.out.println("sql exception for query: " + e);
        }

        return list;
    }


    public Map<String, String> getReportById(String reportId) {
        String query = null;
        query = "select * from test1 WHERE ReportID = '" + reportId + "';";
        System.out.println(query);
        Map<String, String> resultMap = new HashMap<>();
        try {
            Map<String, String> map = new HashMap<>();
            resultMap = jdbcTemplate.queryForObject(query, (rs, rownumber) -> {
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    int type = rsmd.getColumnType(i);
                    if (type == Types.VARCHAR) {
                        map.put(rsmd.getColumnLabel(i), rs.getString(i));
                    } else if (type == Types.INTEGER) {
                        map.put(rsmd.getColumnLabel(i), Integer.toString(rs.getInt(i)));
                    }
                }
                return map;
            });
        } catch (Exception e) {
            System.out.println("sql exception for query: " + e);
        }

        return resultMap;
    }

    public Map<String, String> addReport(Map<String, String> map) {
        Set<String> set = null;
        Map resultMap = new HashMap<>();
        set = iterateColumnName();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!set.contains(entry.getKey())) {
                resultMap.put("success", "fail due to column name not existed");
                return resultMap;
            }
        }
        try {
            Object[] args = buildInsertObject((LinkedHashSet<String>) set, map);
            String sql = concatInsertSQL((LinkedHashSet<String>) set);
            jdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println("error when insert data: " + e);
        }
        resultMap.put("success", "success");
        return resultMap;
    }

    private Set<String> iterateColumnName() {
        Set<String> set = new LinkedHashSet<>();
        String query = "SELECT TOP 1 * FROM Test1 ";
        try {
            Object o = jdbcTemplate.query(query, (rs, rownumber) -> {
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.println("test column label: " + rsmd.getColumnLabel(i));
                    set.add(rsmd.getColumnLabel(i));
                }
                return null;
            });
        } catch (Exception e) {
            System.out.println("error when getting column label: " + e);
        }
//        String query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TESTING'";
//        try {
//           ResultSet resultSet = jdbcTemplate.query(query, (rs, rownumber) -> {
//                while (rs.next()) {
//                    set.add(rs.get);
//                }
//        } catch (Exception e) {
//            System.out.println("error when getting column label: " + e);
//        }
        return set;
    }

    private String concatInsertSQL(LinkedHashSet<String> hashSet) {
        String sql = "insert into test1 (";
        for (String columnLabel: hashSet) {
            sql += columnLabel + ",";
        }
        // delete last colon
        sql = sql.substring(0, sql.length() - 1);
        sql += ") values (";
        for (int i = 1; i < hashSet.size(); i++) {
            sql += "?,";
        }
        sql += "?)";
        System.out.println(sql);
        return sql;
    }

    private Object[] buildInsertObject(LinkedHashSet<String> hashSet, Map<String, String> map) {
        Object[] object = new Object[hashSet.size()];
        int index = 0;
        for (String columnLabel: hashSet) {
            object[index++] = map.get(columnLabel);
        }
        return object;
    }

//    public Map<String, String> addReport(Report report) {
//        String query = null;
//        query = "select * from test WHERE ReportID = '"+reportId+"';";
//        System.out.println(query);
//        Map<String, String> resultMap = new HashMap<>();
//        try {
//            Map<String, String> map = new HashMap<>();
//            resultMap = jdbcTemplate.queryForObject(query, (rs, rownumber) -> {
//                ResultSetMetaData rsmd = rs.getMetaData();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    int type = rsmd.getColumnType(i);
//                    if (type == Types.VARCHAR) {
//                        map.put(rsmd.getColumnLabel(i), rs.getString(i));
//                    } else if (type == Types.INTEGER){
//                        map.put(rsmd.getColumnLabel(i), Integer.toString(rs.getInt(i)));
//                    }
//                }
//                return map;
//            });
//        } catch (Exception e) {
//            System.out.println("sql exception for query: " + e);
//        }
//
//        return resultMap;
//    }

}

