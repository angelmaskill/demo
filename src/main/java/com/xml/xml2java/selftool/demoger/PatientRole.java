package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class PatientRole {
    private String classCode;
    private String id;
    private List<Addr> Addr;
    private String telecom;
    private List<Patient> Patient;
    private List<ProviderOrganization> ProviderOrganization;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAddr(List<Addr> Addr) {
        this.Addr = Addr;
    }

    public List<Addr> getAddr() {
        return Addr;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }

    public String getTelecom() {
        return telecom;
    }

    public void setPatient(List<Patient> Patient) {
        this.Patient = Patient;
    }

    public List<Patient> getPatient() {
        return Patient;
    }

    public void setProviderOrganization(List<ProviderOrganization> ProviderOrganization) {
        this.ProviderOrganization = ProviderOrganization;
    }

    public List<ProviderOrganization> getProviderOrganization() {
        return ProviderOrganization;
    }
}