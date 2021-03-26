package com.designpattern.Chapter24State.sample01;

public class MiddleState extends AbstractState {
    public MiddleState(AbstractState state) {
        this.acc = state.acc;
        this.point = state.getPoint();
        this.stateName = "����";
    }

    public void writeNote(int score) {
        System.out.println(acc.getName() + "��������" + "������" + score + "*2�����֡�");
        this.point += score * 2;
        checkState(score);
        System.out.println("ʣ�����Ϊ��" + this.point + "����ǰ����Ϊ��" + acc.getState().stateName + "��");
    }

    public void checkState(int score) {
        if (point >= 1000) {
            acc.setState(new HighState(this));
        } else if (point < 0) {
            System.out.println("���㣬�ļ�����ʧ�ܣ�");
            this.point += score;
        } else if (point <= 100) {
            acc.setState(new PrimaryState(this));
        }
    }
}