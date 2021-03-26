package com.designpattern.Chapter24State.ShareState;

class Switch {
    private static State state, onState, offState;
    private String name;

    public Switch(String name) {
        this.name = name;
        onState = new OnState();
        offState = new OffState();
        state = onState;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void on() {
        System.out.print(name);
        state.on(this);
    }

    public void off() {
        System.out.print(name);
        state.off(this);
    }

    public static State getState(String type) {
        if (type.equalsIgnoreCase("on")) {
            return onState;
        } else {
            return offState;
        }
    }
}

abstract class State {
    public abstract void on(Switch s);

    public abstract void off(Switch s);
}

class OnState extends State {
    public void on(Switch s) {
        System.out.println("�Ѿ��򿪣�");
    }

    public void off(Switch s) {
        System.out.println("�رգ�");
        s.setState(Switch.getState("off"));

    }
}

class OffState extends State {
    public void on(Switch s) {
        System.out.println("�򿪣�");
        s.setState(Switch.getState("on"));
    }

    public void off(Switch s) {
        System.out.println("�Ѿ��رգ�");
    }
}

class Client {
    public static void main(String args[]) {
        Switch s1, s2;
        s1 = new Switch("����1");
        s2 = new Switch("����2");

        s1.on();
        s2.on();
        s1.off();
        s2.off();
        s2.on();
        s1.on();
    }
}