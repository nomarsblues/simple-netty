package handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.util.List;
import java.util.function.Supplier;

public class ChannelInitializerFactory {

    public static ChannelInitializer<SocketChannel> build(Supplier<List<ChannelHandler>> s) {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                s.get().forEach(h -> socketChannel.pipeline().addLast(h));
            }
        };
    }
}
