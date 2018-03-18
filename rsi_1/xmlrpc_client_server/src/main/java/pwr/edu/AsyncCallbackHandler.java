package pwr.edu;

import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;


public class AsyncCallbackHandler implements AsyncCallback {

    CentralServer.Connection connection;

    public AsyncCallbackHandler(CentralServer.Connection connection) {
        this.connection = connection;
    }

    public void handleResult(Object o, URL url, String s) {
        System.out.println("Handling async result: " + o);
    }

    public void handleError(Exception e, URL url, String s) {
        System.out.println("Handling async error: " + e);
    }
}
