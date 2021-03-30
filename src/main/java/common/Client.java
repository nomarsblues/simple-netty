package common;

public interface Client {
    void connect(String addr);

    void send(Object o);
}
