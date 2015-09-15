package startup;

import connector.HttpServer;

/**
 * Created by YBH on 2015/9/16.
 */
public class Bootstrap {

    public static void main(String[] args){
        HttpServer server = new HttpServer();
        server.await();
    }
}
