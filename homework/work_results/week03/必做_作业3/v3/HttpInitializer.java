package com.geek.homework.week03.v3;

import com.geek.homework.week03.v3.filter.HeaderHttpRequestFilter;
import com.geek.homework.week03.v3.filter.HeaderHttpResponseFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpHandler()
                //添加request和response过滤器
                .addRequestFilter(new HeaderHttpRequestFilter())
                .addResponseFilter(new HeaderHttpResponseFilter())
        );
    }
}
