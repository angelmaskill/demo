package com.bytecode.utils;

public class CountServiceImpl implements CountService {

    private int count = 0;

    public int count() {
        return count++;
    }
} 