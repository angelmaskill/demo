package com.designpattern.Chapter16Proxy.sample01;

public interface AbstractPermission {
    public void modifyUserInfo();

    public void viewNote();

    public void publishNote();

    public void modifyNote();

    public void setLevel(int level);
}