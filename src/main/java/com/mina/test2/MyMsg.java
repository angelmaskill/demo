package com.mina.test2;

import java.nio.charset.Charset;

/** 
 * 自定义协议的消息体 
 */  
public class MyMsg {  
    /** 
     * 消息长度 
     */  
    private Integer lenth;  
  
    /** 
     * 发送人 
     */  
    private Long sender;  
  
    /** 
     * 接收人 
     */  
    private Long receiver;  
  
    /** 
     * 消息内容 
     */  
    private String content;  
      
    public MyMsg() {  
          
    }  
  
    public Long getSender() {  
        return sender;  
    }  
  
    public void setSender(Long sender) {  
        this.sender = sender;  
    }  
  
    public Long getReceiver() {  
        return receiver;  
    }  
  
    public void setReceiver(Long receiver) {  
        this.receiver = receiver;  
    }  
  
    public String getContent() {  
        return content;  
    }  
  
    public void setContent(String content) {  
        this.content = content;  
    }  
  
    /** 
     * 先计算长度，再返回。这里长度包含长度本身的字节 
     */  
    public Integer getLenth() {  
          
        this.lenth = 4 + 8*2 + this.content.getBytes(Charset.forName("utf-8")).length;  
          
        return lenth;  
    }  
  
    public MyMsg(Long sender, Long receiver, String content) {  
        this.sender = sender;  
        this.receiver = receiver;  
        this.content = content;  
    }  
  
    @Override  
    public String toString() {  
        return "MyMsg [lenth=" + this.getLenth() + ", sender=" + sender + ", receiver="  
                + receiver + ", content=" + content + "]";  
    }  
  
}  