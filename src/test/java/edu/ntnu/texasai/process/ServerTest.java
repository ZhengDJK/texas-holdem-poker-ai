package edu.ntnu.texasai.process;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng on 2016/11/23.
 */
public class ServerTest {
    public static void main(String args[])throws IOException{
        List<Socket> sockets=new ArrayList<Socket>();
        ServerSocket sk = new ServerSocket(4001);
        int num=0;
        while (num<2){
            sockets.add(sk.accept());
            num++;
        }
    }
}
