import org.apache.xmlrpc.AsyncCallback;
import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class RpcClient {

    public static final int PORT_AS_SERVER = 10001; //10000 + PC number
    public static final String SERVER_NAME = "myServer2";
    public static final String REMOTE_SERVER_NAME = "myServer1";

    static private XmlRpcClient client;
    static private WebServer webServer;

    public static void main(String[] args) throws MalformedURLException {

        WebServer webServer = new WebServer(PORT_AS_SERVER);
        webServer.addHandler(SERVER_NAME, new RpcClient());
        webServer.start();

        client = new XmlRpcClient("http://localhost:" + 10002);

        System.out.println("Listening on port: " + PORT_AS_SERVER);
        System.out.println("Sending to port: " + 10002);
    }

    public boolean cutshot() {
        System.out.println("cutShot");
        webServer.shutdown();
        return true;
    }

    public boolean pingpong() {
        sleep(1000);
        if (new Random().nextInt(10) > 8) {
            try {
                cutShot(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("PONG");
            try {
                pingpong(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private static void pingpong(XmlRpcClient client) throws XmlRpcException, IOException {
        System.out.println("PING");
        Vector<Boolean> params = new Vector<Boolean>();
        client.execute(REMOTE_SERVER_NAME + ".pingpong", params);
    }

    private static void cutShot(XmlRpcClient client) throws XmlRpcException, IOException {
        System.out.println("SEND CUT_SHOT");
        Vector<Boolean> params = new Vector<Boolean>();
        client.execute(REMOTE_SERVER_NAME + ".cutshot", params);
    }

    private static void serverAsy(XmlRpcClient client, AsyncCallback acHandler) {
        Vector<Integer> params = new Vector<Integer>(2);
        params.add(1000);
        client.executeAsync(SERVER_NAME + ".asy", params, acHandler);
        System.out.println("Async executed.");
    }

    private static Object serverEcho(XmlRpcClient client) throws XmlRpcException, IOException {
        Vector<Integer> params = new Vector<Integer>(2);
        params.addElement(12);
        params.addElement(21);

        Object result = client.execute(SERVER_NAME + ".echo", params);

        System.out.println(result);
        return result;
    }
}
