package connector;

import core.ServletProcessor;
import core.StaticResourceProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by YBH on 2015/9/16.
 */
public class HttpProcessor {


    public HttpProcessor(HttpConnector socket) {
    }

    public void process(Socket socket) {
        InputStream input = null;
        OutputStream output = null;

        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();

            //创建请求对象
            Request request = new Request(input);
            request.parse();

            //创建响应对象
            Response response = new Response(output);
            response.setRequest(request);

            if (request.getUri().startsWith("/servlet")){
                ServletProcessor processor = new ServletProcessor();
                processor.process(request,response);
            }
            else{
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request,response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
