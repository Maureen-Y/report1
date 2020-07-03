package com.api.report.bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"segment", "name", "tenantId", "groupId", "reportId", "grantType", "resource", "clientId", "clientSecret", "clientInfo"})
public class Report implements Serializable {

    private static final long serialVersionUID = 4960164018835526029L;
    private String segment;
    private String name;
    private String tenantId;
    private String groupId;
    private String reportId;
    private String grantType;
    private String resource;
    private String clientId;
    private String clientSecret;
    private int clientInfo;

    public Report() {
        super();
    }

    public Report(String segment, String name, String tenantId, String groupId, String reportId, String grantType,
                String resource, String clientId, String clientSecret, int clientInfo) {
        super();
        this.segment = segment;
        this.name = name;
        this.tenantId = tenantId;
        this.groupId = groupId;
        this.reportId = reportId;
        this.grantType = grantType;
        this.resource = resource;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientInfo = clientInfo;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(int clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Override
    public String toString() {
        return "Report [segment=" + segment + ", name=" + name + ", tenantId=" + tenantId + ", groupId=" + groupId
                + ", reportId=" + reportId + ", grantType=" + grantType + ", resource=" + resource + ", clientId="
                + clientId + ", clientSecret=" + clientSecret + ", clientInfo=" + clientInfo + "]";
    }


}
