package com.netty.test14;

import java.util.HashMap;

import lombok.Data;

@Data
public class ServerImpl {

	private HashMap<Integer, Integer> machMap = null;

	public static ServerImpl getInstance() {
		return new ServerImpl();
	}

}
