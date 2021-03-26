package com.designpattern.Chapter09Singleton.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass extends JFrame {
    private JButton button;
    private JDesktopPane desktopPane;
    private SubFrame iFrame = null;

    public MainClass() {
        super("������");
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        button = new JButton("�������һ���ڲ�����");
        button.addActionListener(new BtListener());
        c.add(button, BorderLayout.SOUTH);

        desktopPane = new JDesktopPane(); //����desktopPane
        c.add(desktopPane);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 400);
        this.show();
    }

    class BtListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (iFrame != null) {
                desktopPane.remove(iFrame);
            }
            iFrame = SubFrame.getFrame();
            desktopPane.add(iFrame);
        }
    }

    public static void main(String[] args) {
        new MainClass();
    }
}