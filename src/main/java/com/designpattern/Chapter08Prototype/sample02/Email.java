package com.designpattern.Chapter08Prototype.sample02;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

public class Email implements Serializable {
    private Attachment attachment = null;

    public Email() {
        this.attachment = new Attachment();
    }

    public Object deepClone() throws IOException, ClassNotFoundException, OptionalDataException {
        //������д������
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        //�����������ȡ��
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (ois.readObject());
    }

    public Attachment getAttachment() {
        return this.attachment;
    }

    public void display() {
        System.out.println("�鿴�ʼ�");
    }

}