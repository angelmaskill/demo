package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class Custodian {
    private String typeCode;
    private List<AssignedCustodian> AssignedCustodian;

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setAssignedCustodian(List<AssignedCustodian> AssignedCustodian) {
        this.AssignedCustodian = AssignedCustodian;
    }

    public List<AssignedCustodian> getAssignedCustodian() {
        return AssignedCustodian;
    }
}