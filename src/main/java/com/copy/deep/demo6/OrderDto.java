package com.copy.deep.demo6;

import java.util.List;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/29 17:49
 * @Modified By:
 */
public class OrderDto {
    private int id;
    private String name;
    List<Address> addressList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
