package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class Location {
    private String typeCode;
    private List<HealthCareFacility> HealthCareFacility;

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setHealthCareFacility(List<HealthCareFacility> HealthCareFacility) {
        this.HealthCareFacility = HealthCareFacility;
    }

    public List<HealthCareFacility> getHealthCareFacility() {
        return HealthCareFacility;
    }
}