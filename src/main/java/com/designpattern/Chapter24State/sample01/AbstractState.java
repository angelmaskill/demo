package com.designpattern.Chapter24State.sample01;

public abstract class AbstractState {
    protected ForumAccount acc;
    protected int point;
    protected String stateName;

    public abstract void checkState(int score);

    public void downloadFile(int score) {
        System.out.println(acc.getName() + "�����ļ����۳�" + score + "���֡�");
        this.point -= score;
        checkState(score);
        System.out.println("ʣ�����Ϊ��" + this.point + "����ǰ����Ϊ��" + acc.getState().stateName + "��");
    }

    public void writeNote(int score) {
        System.out.println(acc.getName() + "��������" + "������" + score + "���֡�");
        this.point += score;
        checkState(score);
        System.out.println("ʣ�����Ϊ��" + this.point + "����ǰ����Ϊ��" + acc.getState().stateName + "��");
    }

    public void replyNote(int score) {
        System.out.println(acc.getName() + "�ظ����ԣ�����" + score + "���֡�");
        this.point += score;
        checkState(score);
        System.out.println("ʣ�����Ϊ��" + this.point + "����ǰ����Ϊ��" + acc.getState().stateName + "��");
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return (this.point);
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return (this.stateName);
    }
}