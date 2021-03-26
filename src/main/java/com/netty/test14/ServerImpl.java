package com.netty.test14;

import lombok.Data;

import java.util.HashMap;

@Data
public class ServerImpl {

    private HashMap<Integer, Integer> machMap = null;

    public static ServerImpl getInstance() {
        return new ServerImpl();
    }

}
