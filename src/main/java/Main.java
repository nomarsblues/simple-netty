import common.Client;
import common.Server;
import simple.SimpleClient;
import simple.SimpleServer;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int port = new Random().nextInt(65535);
        new Thread(() -> {
            Server server = new SimpleServer();
            server.start(port);
        }).start();
        new Thread(() -> {
            Client client = new SimpleClient();
            client.connect(port);
            client.send("hello");
        }).start();
        while (true) {
        }
    }
}
