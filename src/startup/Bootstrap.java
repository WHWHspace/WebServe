package startup;

import connector.HttpConnector;

/**
 * 启动类
 */
public class Bootstrap {

    /**
     * main方法，程序入口
     * @param args
     */
    public static void main(String[] args){
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
