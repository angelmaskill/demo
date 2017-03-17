package com.thread.BlockingQueue.demo1;


import java.util.concurrent.BlockingQueue;  
  
/** 
 * 生产者 
 * **/  
public class Producer  extends Thread{  
  
    /*** 
     * 利用队列存储样品 
     * */  
    private BlockingQueue<String> bq;  
      
    public Producer() {  
        // TODO Auto-generated constructor stub  
    }  
       
    public Producer(BlockingQueue<String> bq) {  
       
        this.bq = bq;  
    }  
  
    @Override  
    public void run() {  
        String []str=new String[]{"solr","lucene","nutch"};   
          
        for(int i=0;i<99999999;i++){  
            System.out.println(getName()+"生产者准备生产集合元素了!");  
            try{  
                Thread.sleep(2000);  
                //尝试放入元素，如果对列已满，则线程被阻塞  
                bq.put(str[i%3]);  
                  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
            System.out.println(getName()+"生产完成:"+bq);  
        }  
          
          
    }  
      
      
}