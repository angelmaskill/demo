package com.xml.xml2java.selftool.demoger;

import java.util.List;
public class Authenticator
{ 
private String time;
private String signatureCode;
private List<AssignedEntity>  AssignedEntity;
public void setTime(String time) {
this.time=time;
}
public String getTime() {
return time;
}
public void setSignatureCode(String signatureCode) {
this.signatureCode=signatureCode;
}
public String getSignatureCode() {
return signatureCode;
}
public void setAssignedEntity(List<AssignedEntity>  AssignedEntity) {
this.AssignedEntity=AssignedEntity;
}
public List<AssignedEntity>  getAssignedEntity() {
return AssignedEntity;
}
}