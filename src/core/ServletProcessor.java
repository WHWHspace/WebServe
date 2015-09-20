package core;

import connector.*;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * servlet请求处理器
 */
public class ServletProcessor {

    //处理servlet请求
    public void process(Request request, Response response) {
        //获取uri形式如下：
        //   /servlet/servletName
        String uri = request.getUri();
        //获取uri中的servletName
        String servletName = uri.substring(uri.lastIndexOf('/') + 1);

        //创建UrlClassLoader
        URLClassLoader loader = null;
        try {
            //UrlClassLoader加载类时所要寻找的目录
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File((Constants.WEB_ROOT));
            //servlet类所在的目录
            String repository = (new URL("file",null,classPath.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            //加载servlet类
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            //创建servlet的实例
            servlet = (Servlet)myClass.newInstance();
            //调用servlet的service方法，传入request和response
            servlet.service((ServletRequest)requestFacade,(ServletResponse)responseFacade);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        catch (Throwable e){
            e.printStackTrace();
        }

    }
}
