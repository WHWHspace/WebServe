package startup;

import connector.HttpConnector;

/**
 * ������
 */
public class Bootstrap {

    /**
     * main�������������
     * @param args
     */
    public static void main(String[] args){
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
