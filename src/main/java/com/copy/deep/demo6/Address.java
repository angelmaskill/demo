package com.copy.deep.demo6;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/29 18:01
 * @Modified By:
 */
public class Address {
    String addressName;
    int streetNo;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public Address(String addressName, int streetNo) {
        this.addressName = addressName;
        this.streetNo = streetNo;
    }
}
