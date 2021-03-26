package com.designpattern.Chapter08Prototype.相似对象的复制;

class Student implements Cloneable {
    private String stuName;
    private String stuSex;
    private int stuAge;
    private String stuMajor;
    private String stuCollege;
    private String stuUniversity;

    public Student(String stuName, String stuSex, int stuAge, String stuMajor, String stuCollege, String stuUniversity) {
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuAge = stuAge;
        this.stuMajor = stuMajor;
        this.stuCollege = stuCollege;
        this.stuUniversity = stuUniversity;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    public void setStuUniversity(String stuUniversity) {
        this.stuUniversity = stuUniversity;
    }

    public String getStuName() {
        return (this.stuName);
    }

    public String getStuSex() {
        return (this.stuSex);
    }

    public int getStuAge() {
        return (this.stuAge);
    }

    public String getStuMajor() {
        return (this.stuMajor);
    }

    public String getStuCollege() {
        return (this.stuCollege);
    }

    public String getStuUniversity() {
        return (this.stuUniversity);
    }

    public Student clone() {
        Student cpStudent = null;
        try {
            cpStudent = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return cpStudent;
    }
}

class MainClass {
    public static void main(String args[]) {
        Student stu1, stu2, stu3;

        stu1 = new Student("���޼�", "��", 24, "�������", "���ѧԺ", "���ϴ�ѧ"); //״̬����

        //ʹ��ԭ��ģʽ
        stu2 = stu1.clone();
        stu2.setStuName("���");

        //ʹ��ԭ��ģʽ
        stu3 = stu1.clone();
        stu3.setStuName("С��Ů");
        stu3.setStuSex("Ů");

        System.out.print("������" + stu1.getStuName());
        System.out.print("���Ա�" + stu1.getStuSex());
        System.out.print("�����䣺" + stu1.getStuAge());
        System.out.print("��רҵ��" + stu1.getStuMajor());
        System.out.print("��ѧԺ��" + stu1.getStuCollege());
        System.out.print("��ѧУ��" + stu1.getStuUniversity());
        System.out.println();

        System.out.print("������" + stu2.getStuName());
        System.out.print("���Ա�" + stu2.getStuSex());
        System.out.print("�����䣺" + stu2.getStuAge());
        System.out.print("��רҵ��" + stu2.getStuMajor());
        System.out.print("��ѧԺ��" + stu2.getStuCollege());
        System.out.print("��ѧУ��" + stu2.getStuUniversity());
        System.out.println();

        System.out.print("������" + stu3.getStuName());
        System.out.print("���Ա�" + stu3.getStuSex());
        System.out.print("�����䣺" + stu3.getStuAge());
        System.out.print("��רҵ��" + stu3.getStuMajor());
        System.out.print("��ѧԺ��" + stu3.getStuCollege());
        System.out.print("��ѧУ��" + stu3.getStuUniversity());
        System.out.println();
    }
}