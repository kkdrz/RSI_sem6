import org.apache.xmlrpc.WebServer;

public class RpcServer {

    public static int PORT = 10001; //10000 + PC number

    public static void main(String[] args) {
        System.out.println("Starting XML-RPC server");

        WebServer webServer = new WebServer(PORT);
        webServer.addHandler("myServer", new RpcServer());
        webServer.start();

        System.out.println("XML-RPC server started on port:" + PORT);
    }

    public Integer echo(int a, int b) {
        System.out.println("Invoked echo with params:    a=" + a + "    b=" + b);
        return a + b;
    }

    public int asy(int sleepTime) {
        System.out.println("Invoked asy: sleepTime=" + sleepTime + "...");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("... asy - end of sleep");
        return 123;
    }


}

