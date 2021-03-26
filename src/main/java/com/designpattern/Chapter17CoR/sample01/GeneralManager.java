package com.designpattern.Chapter17CoR.sample01;

public class GeneralManager extends Leader {
    public GeneralManager(String name) {
        super(name);
    }

    public void handleRequest(LeaveRequest request) {
        if (request.getLeaveDays() < 30) {
            System.out.println("�ܾ���" + name + "����Ա��" + request.getLeaveName() + "����������������Ϊ" + request.getLeaveDays() + "�졣");
        } else {
            System.out.println("Ī��" + request.getLeaveName() + "���ְ����Ȼ���" + request.getLeaveDays() + "�졣");
        }
    }
}