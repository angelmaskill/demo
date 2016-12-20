package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class AsOrganizationPartOf {
    private String classCode;
    private List<WholeOrganization> WholeOrganization;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setWholeOrganization(List<WholeOrganization> WholeOrganization) {
        this.WholeOrganization = WholeOrganization;
    }

    public List<WholeOrganization> getWholeOrganization() {
        return WholeOrganization;
    }
}