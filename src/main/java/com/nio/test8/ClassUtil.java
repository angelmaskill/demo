package com.nio.test8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 操作class的工具类，包括序列化，反序列化，获取类信息
 * */
public class ClassUtil {
    //用于缓存类的Field信息
    private static ConcurrentHashMap<String, List<Field>> classFieldMap = new ConcurrentHashMap<String,List<Field>>();
    //用于缓存类的Field信息
    private static ConcurrentHashMap<String,Method[]> classMethodMap = new ConcurrentHashMap<String,Method[]>();
    /**
     * 序列化
     * @param object 需要序列化的对象
     * */
    public static byte[] object2Bytes(Object object){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        }catch(IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }finally{
            try {
                if(oos!=null)
                    oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }
        return baos.toByteArray();
    }
    /**
     * 反序列化
     * @param bytes 需要反序列化成对象的字节数组
     * */
    public static Object bytes2Object(byte[] bytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
            return object;
        }catch(IOException e){
            throw new RuntimeException(e.getMessage(),e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(),e);
        }finally{
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(),e);
                }
            }
        }
    }
}