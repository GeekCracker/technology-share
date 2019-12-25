package com.technology.share.utils;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.*;

public class RedisUtils {

    private static Jedis jedis;

    public RedisUtils(Jedis jedis){
        this.jedis = jedis;
    }


    public static void setObject(String key,Object object){
        if(!StringUtils.isEmpty(key)){
            synchronized (jedis) {
                try {
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    ObjectOutputStream oos=new ObjectOutputStream(bos);
                    oos.writeObject(object);
                    jedis.set(key.getBytes(), bos.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Object getObject(String key){
        if(!StringUtils.isEmpty(key)){
            synchronized (jedis) {
                byte[] bt=jedis.get(key.getBytes());
                if(bt!=null){
                    ByteArrayInputStream bis=new ByteArrayInputStream(bt);
                    ObjectInputStream ois;
                    try {
                        ois = new ObjectInputStream(bis);
                        Object object= ois.readObject();
                        ois.close();
                        return object;
                    } catch(EOFException e){
                    }catch (IOException e) {
                    } catch (ClassNotFoundException e) {
                    }
                }
            }
        }
        return null;
    }
}
