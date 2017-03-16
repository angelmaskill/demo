package com.netty.test12.domain;

public enum DecodingState {  
    VERSION,  
    TYPE,  
    PAYLOAD_LENGTH,  
    PAYLOAD,  
}  