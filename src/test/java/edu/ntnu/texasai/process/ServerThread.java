package edu.ntnu.texasai.process;

/**
 * Created by zheng on 2016/11/21.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
//供server调用
public class ServerThread extends Thread
{
    private Socket connectToClient;

    private DataInputStream inFromClient;

    private DataOutputStream outToClient;


    public ServerThread(Socket soc) throws IOException
    {
        super();
        connectToClient = soc;
        inFromClient = new DataInputStream(connectToClient.getInputStream());
        outToClient = new DataOutputStream(connectToClient.getOutputStream());
        start();
    }

    public void run()
    {
        try
        {
            String str;

            boolean goon = true;
            while (goon)
            {
                str = inFromClient.readUTF();
                if ( !str.equals("bye") )
                {
                    System.out.println(connectToClient.getPort()+": "+str);
                    str = "return string " + str;

                    outToClient.writeUTF(str);
                    outToClient.flush();
                }
                else
                {
                    goon = false;
                    outToClient.writeUTF("bye");
                    outToClient.flush();
                }
            }
            inFromClient.close();
            outToClient.close();
            connectToClient.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}