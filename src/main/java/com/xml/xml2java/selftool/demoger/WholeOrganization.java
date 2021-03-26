package com.xml.xml2java.selftool.demoger;

public class WholeOrganization {
    private String classCode;
    private String determinerCode;
    private String id;
    private String name;
    private String asOrganizationPartOf;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAsOrganizationPartOf(String asOrganizationPartOf) {
        this.asOrganizationPartOf = asOrganizationPartOf;
    }

    public String getAsOrganizationPartOf() {
        return asOrganizationPartOf;
    }
}