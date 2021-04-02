package com.technology.share.utils;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @description: 远程shell命令执行工具类
 * @author: 朱俊亮
 * @time: 2021-04-02 11:48
 */
public class RemoteShellExecutorUtils {

    private static final int TIME_OUT = 0;// 表示不超时

    /**
     * 执行远程shell命令
     * @param ip 远程服务器IP地址
     * @param username 远程服务器登陆用户名
     * @param password 远程服务器登陆密码
     * @param shell shell脚本命令
     * @return 返回状态码
     */
    public static String exec(String ip,String username,String password,String shell){
        Connection connection = null;
        StringBuilder sb = new StringBuilder("");
        try {
            connection = new Connection(ip);
            connection.connect();
            boolean login = connection.authenticateWithPassword(username, password);
            if(login){
                Session session = connection.openSession();
                session.execCommand(shell);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                InputStream is = session.getStdout();
                BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(is),"UTF-8"));
                String line = null;
                while ((line = br.readLine()) != null){
                    sb.append(line).append("\r\n");
                    System.out.println(line);
                }
                session.getExitStatus();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String ip = "111.231.111.161";
        String username = "root";
        String password = "QIANqianzjl521..";
        String shell = "cd /usr/local/application/technology";
        String result = exec(ip,username,password,shell);
        System.out.println(result);
    }
}
