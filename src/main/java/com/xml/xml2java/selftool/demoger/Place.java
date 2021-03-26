package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class Place {
    private String classCode;
    private String determinerCode;
    private List<Addr> Addr;

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

    public void setAddr(List<Addr> Addr) {
        this.Addr = Addr;
    }

    public List<Addr> getAddr() {
        return Addr;
    }
}