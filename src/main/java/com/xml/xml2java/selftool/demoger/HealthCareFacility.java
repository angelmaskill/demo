package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class HealthCareFacility {
    private String classCode;
    private List<ServiceProviderOrganization> ServiceProviderOrganization;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setServiceProviderOrganization(List<ServiceProviderOrganization> ServiceProviderOrganization) {
        this.ServiceProviderOrganization = ServiceProviderOrganization;
    }

    public List<ServiceProviderOrganization> getServiceProviderOrganization() {
        return ServiceProviderOrganization;
    }
}