package simple;

import common.AbstractClient;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class SimpleClient extends AbstractClient {
    @Override
    protected EventLoopGroup group() {
        return new NioEventLoopGroup();
    }
}
