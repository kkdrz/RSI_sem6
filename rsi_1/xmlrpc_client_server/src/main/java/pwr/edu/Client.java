package pwr.edu;


import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xmlrpc.AsyncCallback;
import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpcClient;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Client implements AsyncCallback {

    private String serverName;
    private String clientName;
    private int clientPort;
    private int number;

    XmlRpcClient client;

    public Client(String clientName, int clientPort, String serverName, String serverUrl, int serverPort) throws MalformedURLException {
        this.clientName = clientName;
        this.clientPort = clientPort;
        this.serverName = serverName;

        WebServer webServer = new WebServer(clientPort);
        webServer.addHandler(clientName, this);
        webServer.start();

        client = new XmlRpcClient(serverUrl + ":" + serverPort);
        Vector<String> params = new Vector<>(Arrays.asList(clientName, "http://localhost:" + clientPort));
        try {
            client.execute(serverName + ".register", params);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem with registration");
        }
    }

    public String echo(String x) {
        System.out.println(x);
        return "Done.";
    }

    public String randomNumber(int min, int max) {
        number = new Random().nextInt((max - min) + 1) + min;
        System.out.println("Secret number is " + number);
        return "I'm ready to start the game!";
    }

    public String guess(int x) {
        if (x == number) {
            return "Congrats, " + x + " was the secret number.";
        }
        if (x < number){
            return "Secret number is bigger than " + x;
        }
        return "Secret number is smaller than " + x;
    }

    public String concatenate(String txt, int number) {
        return txt + number;
    }

    public int countLetter(String txt, String letter) {
        int counter = 0;
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == letter.charAt(0)) counter++;
        }
        return counter;
    }

    public double pow(int base, int exponent) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Math.pow(base, exponent);
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available method in client: ");
        sb.append(clientName);
        sb.append("\nvoid echo(String x) - prints text to client console\n");
        sb.append("String concatenate(String txt, int number) - returns concatenated txt and number\n");
        sb.append("int countLetter(String txt, char letter) - returns number of specific letter in text\n");
        sb.append("double pow(int base, int exponent) - reeeally slow method, returns pow(base,exponent)\n");
        return sb.toString();
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Command: ");
            command = sc.nextLine();
            if ("/q".equals(command)) {
                System.out.println("Closing application..");
                return;
            }
            String[] params = getParams(command);
            if (params.length < 2) {
                System.out.println("Invalid command!");
                continue;
            }
            Object response = null;
            if ("--async".equals(params[0])) {
                System.out.println("Executing async: " + params[2] + " on " + params[1]);
                executeAsync(Arrays.copyOfRange(params, 1, params.length));

            } else {
                System.out.println("Executing: " + params[1] + " on " + params[0]);
                response = execute(params);
            }

            System.out.println("Response:\n" + response);
        }
    }

    private void executeAsync(String[] params) {
        Object[] paramsToSend = addFirst(params, clientName);

        Vector<Object[]> vector = new Vector<>();
        vector.add(paramsToSend);
        try {
            client.executeAsync(serverName + ".execute", vector, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object execute(Object[] params) {
        Object[] paramsToSend = addFirst(params, clientName);

        for (int i = 0; i < paramsToSend.length; i++) {
            if (NumberUtils.isDigits((String) paramsToSend[i])) {
                paramsToSend[i] = NumberUtils.toInt((String) paramsToSend[i]);
            }
        }

        Vector<Object[]> vector = new Vector<>();
        vector.add(paramsToSend);
        Object result;
        try {
            result = client.execute(serverName + ".execute", vector);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    private Object[] addFirst(Object[] params, Object el) {
        Object[] paramsToSend = new Object[params.length + 1];
        paramsToSend[0] = el;

        System.arraycopy(params, 0, paramsToSend, 1, params.length);
        return paramsToSend;
    }

    private static String[] getParams(String command) {
        return command.split("\\s+");
    }

    @Override
    public void handleResult(Object o, URL url, String s) {
        System.out.println("Handling async result: " + o);
    }

    @Override
    public void handleError(Exception e, URL url, String s) {
        System.out.println("Handling async error: " + e);
    }
}
