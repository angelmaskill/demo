package com.designpattern.DTDL.demo2;

public interface UserManager {

	public abstract void addUser(String userId, String userName);

	public abstract void delUser(String userId);

	public abstract String findUser(String userId);

	public abstract void modifyUser(String userId, String userName);

}