import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class RpcServer {

    public static int PORT_AS_SERVER = 10002; //10000 + PC number
    public static final String SERVER_NAME = "myServer1";
    public static final String REMOTE_SERVER_NAME = "myServer2";
    static XmlRpcClient client;
    static WebServer webServer;

    public static void main(String[] args) throws IOException, XmlRpcException {
        System.out.println("Starting XML-RPC server");

        WebServer webServer = new WebServer(PORT_AS_SERVER);
        webServer.addHandler(SERVER_NAME, new RpcServer());
        webServer.start();
        System.out.println("XML-RPC server started on port:" + PORT_AS_SERVER);

        Scanner in = new Scanner(System.in);
        System.out.println("Server port: ");
        int portAsAClient = in.nextInt();

        client = new XmlRpcClient("http://localhost:" + portAsAClient);
        pingpong(client);

    }

    public boolean cutshot() {
        System.out.println("cutShot");
        webServer.shutdown();
        System.out.println("Webserver shutdown");
        return true;
    }

    public boolean pingpong() {
        sleep(1000);
        if (new Random().nextInt(10) > 9) {
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

    public Integer echo(int a, int b) {
        System.out.println("Invoked echo with params:    a=" + a + "    b=" + b);
        return a + b;
    }

    public int asy(int sleepTime) {
        System.out.println("Invoked asy: sleepTime=" + sleepTime + "...");
        sleep(sleepTime);
        System.out.println("... asy - end of sleep");
        return 123;
    }


}

