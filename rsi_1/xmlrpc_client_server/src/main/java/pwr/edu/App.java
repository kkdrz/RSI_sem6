package pwr.edu;


import org.apache.commons.lang3.math.NumberUtils;

import java.net.MalformedURLException;
import java.util.Scanner;

public class App {

    private static int REQUIRED_PARAMS_CLIENT = 5;
    private static int REQUIRED_PARAMS_SERVER = 3;
    private static int REQUIRED_PARAMS = 1;

    public static void main(String[] args) {
        Role role = getRoleFromParams(args);

        if (role == Role.CLIENT) {
            System.out.println("Running client..");
            if (!runClient(args)) return;
            System.out.println("Client is running..\nYou can execute commands using this format:\n [client_name] [command] <optional: params>\nIf you want to finish type: /q");
        }
        if (role == Role.SERVER) {
            CentralServer server = new CentralServer(args[1], NumberUtils.toInt(args[2]));
            System.out.println("Server is running..");
        }

    }

    private static boolean runClient(String[] args) {
        try {
            Client client = new Client(args[1], NumberUtils.toInt(args[2]), args[3], args[4], NumberUtils.toInt(args[5]));
            client.listen();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private static Role getRoleFromParams(String[] args) {
        if (args.length >= REQUIRED_PARAMS) {
            if ("--server".equals(args[0])) {
                if (args.length < REQUIRED_PARAMS_SERVER) {
                    System.out.println("You have to pass proper params.\nIn case that you want to run server: --server [server_name] [server_port]");
                    return null;
                }
                if (!NumberUtils.isDigits(args[2])) {
                    System.out.println("\'" + args[2] + "\' is not valid server port number.");
                }
                return Role.SERVER;
            }

            if ("--client".equals(args[0])) {
                if (args.length < REQUIRED_PARAMS_CLIENT) {
                    System.out.println("You have to pass proper params.\nIn case that you want to run client: --client [client_name] [client_port] [server_name] [server_url] [server_port] ");
                    return null;
                }
                if (!NumberUtils.isDigits(args[2])) {
                    System.out.println("\'" + args[2] + "\' is not valid client port number.");
                }
                if (!NumberUtils.isDigits(args[5])) {
                    System.out.println("\'" + args[5] + "\' is not valid server port number.");
                }
                return Role.CLIENT;
            }
        }

        System.out.println("You have to pass proper params.\n\nServer params: --server [server_name] [server_port]\nClient params: --client [client_name] [client_port] [server_name] [server_url] [server_port]");
        return null;
    }


    public enum Role {SERVER, CLIENT}
}

