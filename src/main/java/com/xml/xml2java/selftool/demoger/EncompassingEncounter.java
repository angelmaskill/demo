package com.xml.xml2java.selftool.demoger;

import java.util.List;

public class EncompassingEncounter {
    private String classCode;
    private String moodCode;
    private List<Code> Code;
    private List<EffectiveTime> EffectiveTime;
    private List<Location> Location;

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setMoodCode(String moodCode) {
        this.moodCode = moodCode;
    }

    public String getMoodCode() {
        return moodCode;
    }

    public void setCode(List<Code> Code) {
        this.Code = Code;
    }

    public List<Code> getCode() {
        return Code;
    }

    public void setEffectiveTime(List<EffectiveTime> EffectiveTime) {
        this.EffectiveTime = EffectiveTime;
    }

    public List<EffectiveTime> getEffectiveTime() {
        return EffectiveTime;
    }

    public void setLocation(List<Location> Location) {
        this.Location = Location;
    }

    public List<Location> getLocation() {
        return Location;
    }
}