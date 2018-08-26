package com.xml.xml2java.selftool.demoger;

import java.util.List;
public class Patient
{ 
private String classCode;
private String determinerCode;
private String id;
private String name;
private String administrativeGenderCode;
private String birthTime;
private String maritalStatusCode;
private String ethnicGroupCode;
private List<Birthplace>  Birthplace;
private String nationality;
private String age;
private List<EmployerOrganization>  EmployerOrganization;
private List<Household>  Household;
private List<NativePlace>  NativePlace;
private List<Occupation>  Occupation;
public void setClassCode(String classCode) {
this.classCode=classCode;
}
public String getClassCode() {
return classCode;
}
public void setDeterminerCode(String determinerCode) {
this.determinerCode=determinerCode;
}
public String getDeterminerCode() {
return determinerCode;
}
public void setId(String id) {
this.id=id;
}
public String getId() {
return id;
}
public void setName(String name) {
this.name=name;
}
public String getName() {
return name;
}
public void setAdministrativeGenderCode(String administrativeGenderCode) {
this.administrativeGenderCode=administrativeGenderCode;
}
public String getAdministrativeGenderCode() {
return administrativeGenderCode;
}
public void setBirthTime(String birthTime) {
this.birthTime=birthTime;
}
public String getBirthTime() {
return birthTime;
}
public void setMaritalStatusCode(String maritalStatusCode) {
this.maritalStatusCode=maritalStatusCode;
}
public String getMaritalStatusCode() {
return maritalStatusCode;
}
public void setEthnicGroupCode(String ethnicGroupCode) {
this.ethnicGroupCode=ethnicGroupCode;
}
public String getEthnicGroupCode() {
return ethnicGroupCode;
}
public void setBirthplace(List<Birthplace>  Birthplace) {
this.Birthplace=Birthplace;
}
public List<Birthplace>  getBirthplace() {
return Birthplace;
}
public void setNationality(String nationality) {
this.nationality=nationality;
}
public String getNationality() {
return nationality;
}
public void setAge(String age) {
this.age=age;
}
public String getAge() {
return age;
}
public void setEmployerOrganization(List<EmployerOrganization>  EmployerOrganization) {
this.EmployerOrganization=EmployerOrganization;
}
public List<EmployerOrganization>  getEmployerOrganization() {
return EmployerOrganization;
}
public void setHousehold(List<Household>  Household) {
this.Household=Household;
}
public List<Household>  getHousehold() {
return Household;
}
public void setNativePlace(List<NativePlace>  NativePlace) {
this.NativePlace=NativePlace;
}
public List<NativePlace>  getNativePlace() {
return NativePlace;
}
public void setOccupation(List<Occupation>  Occupation) {
this.Occupation=Occupation;
}
public List<Occupation>  getOccupation() {
return Occupation;
}
}