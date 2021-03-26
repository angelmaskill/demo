package com.designpattern.Chapter09Singleton.other;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class SubFrame extends JInternalFrame {
    private static SubFrame frame;//��̬ʵ��

    /*
     *˽�й��캯��
     */
    private SubFrame() {
        super("�Ӵ���", true, true, true, false);
        this.setLocation(20, 20);  //�����ڲ�����λ��
        this.setSize(200, 200);  //�����ڲ������С
        this.addInternalFrameListener(new MyIFListener());//���������¼�
        this.setVisible(true);
    }

    public static SubFrame getFrame()//���ش���ʵ��
    {
        if (frame == null)//����������Ϊ��
        {
            frame = new SubFrame();//�����ô��壬����ֱ�ӷ��ش���
        }
        return frame;//���ش���
    }

    class MyIFListener extends InternalFrameAdapter//�¼�������
    {
        //�Ӵ���ر�ʱ�������������Ϊnull
        public void internalFrameClosing(InternalFrameEvent e) {
            if (frame != null) {
                frame = null;
            }
        }
    }
}