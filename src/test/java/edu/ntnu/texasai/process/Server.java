package edu.ntnu.texasai.process;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zheng on 2016/11/21.
 */
public class Server
{

    public static void main(String[] args){
        try{
            System.out.println("等待连接");
            ServerSocket sk = new ServerSocket(4001);
            Socket connectToClient = null;
            while(true){
                connectToClient=sk.accept();
                System.out.println(connectToClient.getInetAddress()+"建立连接 "+connectToClient.getPort());
                new ServerThread(connectToClient);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}