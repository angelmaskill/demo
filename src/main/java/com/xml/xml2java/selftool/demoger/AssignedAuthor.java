package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class AssignedAuthor {
    private String id;
    private List<Code> Code;
    private List<AssignedPerson> AssignedPerson;
    private List<RepresentedOrganization> RepresentedOrganization;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCode(List<Code> Code) {
        this.Code = Code;
    }

    public List<Code> getCode() {
        return Code;
    }

    public void setAssignedPerson(List<AssignedPerson> AssignedPerson) {
        this.AssignedPerson = AssignedPerson;
    }

    public List<AssignedPerson> getAssignedPerson() {
        return AssignedPerson;
    }

    public void setRepresentedOrganization(List<RepresentedOrganization> RepresentedOrganization) {
        this.RepresentedOrganization = RepresentedOrganization;
    }

    public List<RepresentedOrganization> getRepresentedOrganization() {
        return RepresentedOrganization;
    }
}