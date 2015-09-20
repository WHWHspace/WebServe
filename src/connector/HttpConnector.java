package connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by YBH on 2015/9/16.
 */
public class HttpConnector implements Runnable{

    boolean stopped;
    private String scheme = "http";

    public String getScheme(){
        return scheme;
    }

    @Override
    public void run() {
        //新建serverSocket,监听本机8080端口的请求
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            System.exit(1);
        }

        //循环，建立连接并处理请求
        while(!stopped){
            Socket socket = null;

            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }

            //新建HttpProcessor,处理请求
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    /**
     * 新建并开始connector线程
     */
    public void start(){
        //将自己作为参数，新建线程
        Thread thread = new Thread(this);
        thread.start();
    }
}
