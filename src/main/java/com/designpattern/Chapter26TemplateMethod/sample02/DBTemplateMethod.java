package com.designpattern.Chapter26TemplateMethod.sample02;

abstract class DBOperator {
    public abstract void connDB();

    public void openDB() {
        System.out.println("�����ݿ�");
    }

    public void useDB() {
        System.out.println("ʹ�����ݿ�");
    }

    public void closeDB() {
        System.out.println("�ر����ݿ�");
    }

    public void process() {
        connDB();
        openDB();
        useDB();
        closeDB();
    }
}

class DBOperatorSubA extends DBOperator {
    public void connDB() {
        System.out.println("ʹ��JDBC-ODBC�Ž��������ݿ�");
    }
}

class DBOperatorSubB extends DBOperator {
    public void connDB() {
        System.out.println("ʹ�����ӳ��������ݿ�");
    }
}

class Client {
    public static void main(String a[]) {
        DBOperator db1;

        db1 = new DBOperatorSubA();
        db1.process();
        System.out.println("---------------------------------------");
        db1 = new DBOperatorSubB();
        db1.process();
    }
}
