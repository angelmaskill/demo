package com.designpattern.Chapter26TemplateMethod.HookMethodDemo;

public abstract class HookDemo {
    public abstract void getData();

    public void convertData() {
        System.out.println("ͨ�õ�����ת��������");
    }

    public abstract void displayData();

    public void process() {
        getData();
        if (isValid()) {
            convertData();
        }
        displayData();
    }

    public boolean isValid() {
        return true;
    }
}

class SubHookDemo extends HookDemo {
    public void getData() {
        System.out.println("��XML�����ļ��л�ȡ���ݡ�");
    }

    public void displayData() {
        System.out.println("����״ͼ��ʾ���ݡ�");
    }

    public boolean isValid() {
        return false;
    }
}

class Client {
    public static void main(String a[]) {
        HookDemo hd;

        hd = new SubHookDemo();
        hd.process();
    }
}
