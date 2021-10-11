package com.geek.homework.week03.v3.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse response) {
        System.out.println("===> 执行Http响应过滤器");
        response.headers().set("kk", "java-1-nio");
    }
}
