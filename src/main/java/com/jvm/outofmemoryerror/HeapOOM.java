package com.jvm.outofmemoryerror;

/**
 * VM Args:-Xms20m -Xmx20m -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:/temp/10011
 *
 * @author owisho
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
//		List<OOMObject> list = new ArrayList<OOMObject>();
//		
//		while (true) {
//			list.add(new OOMObject());
//		}
        printJVMVersion();
        OOM();

    }

    public static void OOM() {
        String s = "adfsfdsfdsfsdfdsfdsfdsfsdfdsf";
        while (true) {
            s = s + s;
        }
    }

    public static void printJVMVersion() {
        System.out.println("jvm 实现版本：\t" + System.getProperty("java.vm.version"));
        System.out.println("jvm 规范版本：\t" + System.getProperty("java.vm.specification.version"));
    }
}
