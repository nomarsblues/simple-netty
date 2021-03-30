package simple;

import common.AbstractClient;
import io.netty.buffer.Unpooled;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.CharsetUtil;

public class SimpleClient extends AbstractClient {
    @Override
    protected EventLoopGroup group() {
        return new NioEventLoopGroup();
    }

    @Override
    public void send(Object o) {
        try {
            sender.channel().writeAndFlush(Unpooled.copiedBuffer(o.toString(), CharsetUtil.UTF_8));
            System.out.println("client send " + o.toString());
        } catch (Exception e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }
}
