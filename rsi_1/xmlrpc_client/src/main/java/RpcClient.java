import org.apache.xmlrpc.AsyncCallback;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.util.Vector;

public class RpcClient {

    public static final int PORT = 10001; //10000 + PC number
    public static final String SERVER = "myServer";

    public static void main(String[] args) {
        try {
            XmlRpcClient client = new XmlRpcClient("http://localhost:" + PORT);

            serverEcho(client);

            AsyncCallbackHandler acHandler = new AsyncCallbackHandler();
            serverAsy(client, acHandler);

            serverEcho(client);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void serverAsy(XmlRpcClient client, AsyncCallback acHandler) {
        Vector<Integer> params = new Vector<Integer>(2);
        params.add(1000);
        client.executeAsync(SERVER + ".asy", params, acHandler);
        System.out.println("Async executed.");
    }

    private static Object serverEcho(XmlRpcClient client) throws XmlRpcException, IOException {
        Vector<Integer> params = new Vector<Integer>(2);
        params.addElement(12);
        params.addElement(21);

        Object result = client.execute(SERVER + ".echo", params);

        System.out.println(result);
        return result;
    }
}
