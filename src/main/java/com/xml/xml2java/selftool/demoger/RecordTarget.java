package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class RecordTarget {
    private String typeCode;
    private String contextControlCode;
    private List<PatientRole> PatientRole;

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setContextControlCode(String contextControlCode) {
        this.contextControlCode = contextControlCode;
    }

    public String getContextControlCode() {
        return contextControlCode;
    }

    public void setPatientRole(List<PatientRole> PatientRole) {
        this.PatientRole = PatientRole;
    }

    public List<PatientRole> getPatientRole() {
        return PatientRole;
    }
}