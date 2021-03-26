package com.file.monitorChange.demo1;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatchTask implements Runnable {
    private String fileDirectory;

    public FileWatchTask(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public void run() {
        WatchService watchService = null;
        try {
            // 获取当前文件系统的WatchService监控对象
            watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 获取文件目录下的Path对象注册到 watchService中。
            // 监听的事件类型，有创建，删除，以及修改
            Paths.get(fileDirectory).register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            WatchKey key = null;
            try {
                // 获取可用key.没有可用的就wait
                key = watchService.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (WatchEvent<?> event : key.pollEvents()) {
                // todo
                System.out.println(event.context() + "文件:" + event.kind() + "次数: " + event.count());
            }
            // 重置，这一步很重要，否则当前的key就不再会获取将来发生的事件
            boolean valid = key.reset();
            // 失效状态，退出监听
            if (!valid) {
                break;
            }
        }
    }
}