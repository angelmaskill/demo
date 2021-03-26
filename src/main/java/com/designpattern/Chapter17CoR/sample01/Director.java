package com.designpattern.Chapter17CoR.sample01;

public class Director extends Leader {
    public Director(String name) {
        super(name);
    }

    public void handleRequest(LeaveRequest request) {
        if (request.getLeaveDays() < 3) {
            System.out.println("����" + name + "����Ա��" + request.getLeaveName() + "����������������Ϊ" + request.getLeaveDays() + "�졣");
        } else {
            if (this.successor != null) {
                this.successor.handleRequest(request);
            }
        }
    }
}