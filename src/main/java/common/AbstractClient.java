package common;

import handler.ChannelInitializerFactory;
import handler.ClientHandlerFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public abstract class AbstractClient implements Client {

    protected ChannelFuture sender;

    protected EventLoopGroup group = group();
    @Override
    public void connect(int port) {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(ChannelInitializerFactory.build(ClientHandlerFactory::simpleHandlers));
            sender = bootstrap.connect("127.0.0.1", port).sync();
            System.out.println("connect success " + port);
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }

    @Override
    public void send(Object o) {
        try {
            System.out.println("client send " + o.toString());
            sender.channel().writeAndFlush(o).sync();
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }


    protected abstract EventLoopGroup group();
}
