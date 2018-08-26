package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class AssignedCustodian
{ 
private String classCode;
private List<RepresentedCustodianOrganization>  RepresentedCustodianOrganization;
public void setClassCode(String classCode) {
this.classCode=classCode;
}
public String getClassCode() {
return classCode;
}
public void setRepresentedCustodianOrganization(List<RepresentedCustodianOrganization>  RepresentedCustodianOrganization) {
this.RepresentedCustodianOrganization=RepresentedCustodianOrganization;
}
public List<RepresentedCustodianOrganization>  getRepresentedCustodianOrganization() {
return RepresentedCustodianOrganization;
}
}