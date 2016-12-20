package com.file.monitorChange.demo2;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class ZJPFileMonitor {  
    
    FileAlterationMonitor monitor = null;  
    public ZJPFileMonitor(long interval) throws Exception {  
        monitor = new FileAlterationMonitor(interval);  
    }  
  
    public void monitor(String path, FileAlterationListener listener) {  
        FileAlterationObserver observer = new FileAlterationObserver(new File(path));  
        monitor.addObserver(observer);  
        observer.addListener(listener);  
    }  
    public void stop() throws Exception{  
        monitor.stop();  
    }  
    public void start() throws Exception {  
        monitor.start();  
    }  
    public static void main(String[] args) throws Exception {  
        ZJPFileMonitor m = new ZJPFileMonitor(5000);  
        m.monitor("C:\\temp",new ZJPFileListener());  
        m.start();  
    }
}