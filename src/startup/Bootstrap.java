package startup;

import connector.HttpConnector;

/**
 * Created by YBH on 2015/9/16.
 */
public class Bootstrap {

    public static void main(String[] args){
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
