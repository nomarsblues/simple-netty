package common;

public interface Client {
    void connect(int port);

    void send(Object o);
}
