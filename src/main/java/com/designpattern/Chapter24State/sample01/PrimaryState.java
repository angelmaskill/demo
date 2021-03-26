package com.designpattern.Chapter24State.sample01;

public class PrimaryState extends AbstractState {
    public PrimaryState(AbstractState state) {
        this.acc = state.acc;
        this.point = state.getPoint();
        this.stateName = "����";
    }

    public PrimaryState(ForumAccount acc) {
        this.point = 0;
        this.acc = acc;
        this.stateName = "����";
    }

    public void downloadFile(int score) {
        System.out.println("�Բ���" + acc.getName() + "����û�������ļ���Ȩ�ޣ�");
    }

    public void checkState(int score) {
        if (point >= 1000) {
            acc.setState(new HighState(this));
        } else if (point >= 100) {
            acc.setState(new MiddleState(this));
        }
    }
}