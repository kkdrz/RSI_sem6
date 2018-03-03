import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;


public class AsyncCallbackHandler implements AsyncCallback {

    public void handleResult(Object o, URL url, String s) {
        System.out.println("Handling async result: " + o);
    }

    public void handleError(Exception e, URL url, String s) {
        System.out.println("Handling async error: " + e);
    }
}
