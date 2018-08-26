package com.xml.xml2java.selftool.demoger;

import java.util.List;
public class Author
{ 
private String time;
private List<AssignedAuthor>  AssignedAuthor;
public void setTime(String time) {
this.time=time;
}
public String getTime() {
return time;
}
public void setAssignedAuthor(List<AssignedAuthor>  AssignedAuthor) {
this.AssignedAuthor=AssignedAuthor;
}
public List<AssignedAuthor>  getAssignedAuthor() {
return AssignedAuthor;
}
}