package com.netty.test4.cli;

import com.netty.Util.ByteBufToBytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;


public class ClientInitHandler extends ChannelInboundHandlerAdapter {
    private ByteBufToBytes reader;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            if (HttpHeaders.isContentLengthSet(response)) {
                reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(response));
            }
        }

        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf content = httpContent.content();
            reader.reading(content);
            content.release();

            if (reader.isEnd()) {
                String resultStr = new String(reader.readFull());
                System.out.println("Server said:" + resultStr);
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

} 