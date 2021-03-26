package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class EmployerOrganization {
    private String name;
    private String telecom;
    private List<Addr> Addr;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }

    public String getTelecom() {
        return telecom;
    }

    public void setAddr(List<Addr> Addr) {
        this.Addr = Addr;
    }

    public List<Addr> getAddr() {
        return Addr;
    }
}