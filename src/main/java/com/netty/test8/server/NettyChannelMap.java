package com.netty.test8.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储SocketChannel
 *
 * @author buer
 * @since 16/6/28
 */
public class NettyChannelMap {

    private static Map<String, SocketChannel> channelMap = new ConcurrentHashMap<String, SocketChannel>();

    public static String add(SocketChannel channel) {
        ChannelId channelId = channel.id();
        String idStr = channelId.asLongText();
        channelMap.put(idStr, channel);
        return idStr;
    }

    public static Channel get(String clientId) {
        return channelMap.get(clientId);
    }

    public static void remove(SocketChannel socketChannel) {
        ChannelId channelId = socketChannel.id();
        String idStr = channelId.asLongText();
        channelMap.remove(idStr);
    }

    public static int getSize() {
        return channelMap.size();
    }

    public static Set<String> getKeys() {
        return channelMap.keySet();
    }

}

