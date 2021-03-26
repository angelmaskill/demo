package com.monitor.demo1;

/**
 * add by myl at 2017年3月7日 下午3:42:38
 * webApp的相关信息
 */

public class WebApp {

    @Override
    public String toString() {
        return "WebApp [name=" + name + ", status=" + status + "]";
    }

    /**
     * 项目名称
     */
    private String name;

    /**
     * 运行状态 "成功" or "停止"
     */
    private String status;


    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
