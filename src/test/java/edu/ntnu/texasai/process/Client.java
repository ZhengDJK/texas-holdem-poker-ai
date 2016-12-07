package edu.ntnu.texasai.process;

/**
 * Created by zheng on 2016/11/21.
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Socket connectionToServer = new Socket("localhost", 4001);
            DataInputStream inFromServer = new DataInputStream(connectionToServer.getInputStream());
            DataOutputStream outToServer = new DataOutputStream(connectionToServer.getOutputStream());
            System.out.println("input your number now:");
            String outStr, inStr;
            boolean goon = true;
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            while (goon)
            {
                outStr = buf.readLine();
                outToServer.writeUTF(outStr);
                outToServer.flush();
                inStr = inFromServer.readUTF();
                if ( !inStr.equals("bye") )
                    System.out.println("the returned data is:"+inStr);
                else
                    goon = false;
            }
            inFromServer.close();
            outToServer.close();
            connectionToServer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}