package pwr.edu;


import org.apache.xmlrpc.AsyncCallback;
import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class CentralServer implements AsyncCallback {

    List<Connection> connections;

    public CentralServer(String serverName, int serverPort) {
        connections = new ArrayList<>();

        WebServer webServer = new WebServer(serverPort);
        webServer.addHandler(serverName, this);
        webServer.start();
    }

    public boolean register(String name, String url) {
        try {
            connections.add(new Connection(name, url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Registered client: " + name + " with URL: " + url);
        return true;
    }

    public String executeAsync(Vector<String> tmp) throws XmlRpcException, IOException {
        String[] params = tmp.toArray(new String[tmp.size()]);

        if (getConnection(params[0]) == null) {
            return "You have to register first.";
        }

        Connection conn = getConnection(params[1]);
        if (conn == null) {
            return "Requested resource is not registered.";
        }


        System.out.println("From \'" + params[0] + "\' to \'" + params[1] + "\': execute async \'" + params[2] + "\'");
        Vector<String> vector = new Vector<String>(Arrays.asList(Arrays.copyOfRange(params, 3, params.length)));
        conn.client.executeAsync(params[1] + "." + params[2], vector, this);
        return "async....";
    }

    public Object execute(Vector<Object> params) throws XmlRpcException, IOException {
        Connection conn = getConnection((String) params.get(0));
        if (conn == null) {
            return "You have to register first.";
        }
        conn = getConnection((String) params.get(1));
        if (conn == null) {
            return "Requested resource is not registered.";
        }
        System.out.println("From \'" + params.get(0) + "\' to \'" + params.get(1) + "\': execute \'" + params.get(2) + "\'");
        return conn.client.execute(params.get(1) + "." + params.get(2), new Vector<Object>(params.subList(3, params.size())));
    }

    private Connection getConnection(String clientName) {
        for (Connection connection : connections) {
            if (connection.name.equals(clientName)) return connection;
        }
        return null;
    }

    public List<String> getRegisteredConnections() {
        List<String> result = new ArrayList<>();
        for (Connection c : connections) {
            result.add(c.name + "  :  " + c.url);
        }
        return result;
    }

    @Override
    public void handleResult(Object o, URL url, String s) {

    }

    @Override
    public void handleError(Exception e, URL url, String s) {

    }

    public class Connection {
        String name;
        String url;
        XmlRpcClient client;

        Connection(String name, String url) throws MalformedURLException {
            this.name = name;
            this.url = url;
            client = new XmlRpcClient(url);
        }
    }


}
