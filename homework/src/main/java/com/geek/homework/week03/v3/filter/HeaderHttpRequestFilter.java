package com.geek.homework.week03.v3.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        System.out.println("===> 执行Http请求过滤器");
        fullRequest.headers().set("mao", "soul");
    }
}
