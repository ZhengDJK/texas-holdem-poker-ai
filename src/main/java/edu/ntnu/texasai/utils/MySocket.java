package edu.ntnu.texasai.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by zheng on 2016/11/24.
 */
public class MySocket {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    public MySocket(Socket socket) throws IOException {
        this.socket=socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    public void sendMsg(String msg) throws IOException {
        out.writeUTF(msg);
        out.flush();
    }

    public String getMsg() throws IOException {
        return in.readUTF();
    }
}
