package com.netty.test12.domain;


import lombok.Data;


@Data
public class Envelope {
    private Version version;
    private Type type;
    private byte[] payload;

    public Envelope() {
    }

    public Envelope(Version version, Type type, byte[] payload) {
        this.version = version;
        this.type = type;
        this.payload = payload;
    }

    public void setType(Object fromByte) {
        // TODO Auto-generated method stub
    }

    // getters & setters
}