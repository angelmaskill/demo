package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class ServiceProviderOrganization {
    private String classCode;
    private String determinerCode;
    private List<AsOrganizationPartOf> AsOrganizationPartOf;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setDeterminerCode(String determinerCode) {
        this.determinerCode = determinerCode;
    }

    public String getDeterminerCode() {
        return determinerCode;
    }

    public void setAsOrganizationPartOf(List<AsOrganizationPartOf> AsOrganizationPartOf) {
        this.AsOrganizationPartOf = AsOrganizationPartOf;
    }

    public List<AsOrganizationPartOf> getAsOrganizationPartOf() {
        return AsOrganizationPartOf;
    }
}