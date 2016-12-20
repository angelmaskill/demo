package com.xml.xml2java.selftool.demoger;

import java.util.List;
public class AssignedEntity
{ 
private String id;
private List<Code>  Code;
private List<AssignedPerson>  AssignedPerson;
public void setId(String id) {
this.id=id;
}
public String getId() {
return id;
}
public void setCode(List<Code>  Code) {
this.Code=Code;
}
public List<Code>  getCode() {
return Code;
}
public void setAssignedPerson(List<AssignedPerson>  AssignedPerson) {
this.AssignedPerson=AssignedPerson;
}
public List<AssignedPerson>  getAssignedPerson() {
return AssignedPerson;
}
}