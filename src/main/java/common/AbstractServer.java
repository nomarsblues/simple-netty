package common;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Optional;
import java.util.Random;

public abstract class AbstractServer implements Server {
    public String start() {
        int port = new Random().nextInt(65535);
        EventLoopGroup bossGroup = bossGroup();
        EventLoopGroup workerGroup = workerGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            bootStrap(bootstrap, bossGroup, workerGroup);
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("start netty on " + port);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            Optional.ofNullable(bossGroup).ifPresent(EventExecutorGroup::shutdownGracefully);
            Optional.ofNullable(workerGroup).ifPresent(EventExecutorGroup::shutdownGracefully);
        }
        return "127.0.0.1:" + port;
    }

    protected abstract void bootStrap(ServerBootstrap bootstrap, EventLoopGroup bossGroup, EventLoopGroup workerGroup);

    protected abstract EventLoopGroup bossGroup();

    protected abstract EventLoopGroup workerGroup();
}
