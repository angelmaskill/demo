/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netty.test14;

/**
 * @author songxuejun
 */
public class MessageModel implements java.io.Serializable {
    private String tag;//
    private int type;//
    private int protocolVer;//
    private int machNo;//
    private int length;//
    private String protocolNo;
    private String data;//
    private String purData;//
    private String crc;//

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public String getPurData() {
        return purData;
    }

    public void setPurData(String purData) {
        this.purData = purData;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getProtocolVer() {
        return protocolVer;
    }

    public void setProtocolVer(int protocolVer) {
        this.protocolVer = protocolVer;
    }

    public int getMachNo() {
        return machNo;
    }

    public void setMachNo(int machNo) {
        this.machNo = machNo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

}
