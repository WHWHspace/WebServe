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
 * servlet��������
 */
public class ServletProcessor {

    //����servlet����
    public void process(Request request, Response response) {
        //��ȡuri��ʽ���£�
        //   /servlet/servletName
        String uri = request.getUri();
        //��ȡuri�е�servletName
        String servletName = uri.substring(uri.lastIndexOf('/') + 1);

        //����UrlClassLoader
        URLClassLoader loader = null;
        try {
            //UrlClassLoader������ʱ��ҪѰ�ҵ�Ŀ¼
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File((Constants.WEB_ROOT));
            //servlet�����ڵ�Ŀ¼
            String repository = (new URL("file",null,classPath.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            //����servlet��
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            //����servlet��ʵ��
            servlet = (Servlet)myClass.newInstance();
            //����servlet��service����������request��response
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
