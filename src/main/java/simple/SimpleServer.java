package simple;

import common.AbstractServer;
import handler.ChannelInitializerFactory;
import handler.ServerHandlerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class SimpleServer extends AbstractServer {

    @Override
    protected void bootStrap(ServerBootstrap bootstrap, EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        bootstrap.group(bossGroup, workerGroup)
                .childHandler(ChannelInitializerFactory.build(ServerHandlerFactory::simpleHandlers));
    }

    protected EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    protected EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }
}
