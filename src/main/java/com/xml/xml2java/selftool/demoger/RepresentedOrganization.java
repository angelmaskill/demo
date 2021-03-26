package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class RepresentedOrganization {
    private String id;
    private String name;
    private List<AsOrganizationPartOf> AsOrganizationPartOf;

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

    public void setAsOrganizationPartOf(List<AsOrganizationPartOf> AsOrganizationPartOf) {
        this.AsOrganizationPartOf = AsOrganizationPartOf;
    }

    public List<AsOrganizationPartOf> getAsOrganizationPartOf() {
        return AsOrganizationPartOf;
    }
}