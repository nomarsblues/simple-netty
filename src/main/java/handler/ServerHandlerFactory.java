package handler;

import com.google.common.collect.Lists;
import io.netty.channel.ChannelHandler;

import java.util.List;

public class ServerHandlerFactory {
    public static List<ChannelHandler> simpleHandlers() {
        return Lists.newArrayList(new SimpleHandler());
    }
}
