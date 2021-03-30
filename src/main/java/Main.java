import common.Server;
import simple.SimpleServer;

public class Main {

    public static void main(String[] args) {
        Server server = new SimpleServer();
        server.start();
    }
}
