package com.geek.homework.week03.v3;

import com.geek.homework.week02.nio.HttpClientDemo;
import com.geek.homework.week03.v3.filter.HttpRequestFilter;
import com.geek.homework.week03.v3.filter.HttpResponseFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends ChannelInboundHandlerAdapter {

    private List<HttpRequestFilter> requestFilterList = new ArrayList<HttpRequestFilter>();
    private List<HttpResponseFilter> responseFilterList = new ArrayList<HttpResponseFilter>();

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println("channelRead流量接口请求开始，时间为:" + System.currentTimeMillis());
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            System.out.println("===> 接收到的请求url为:" + uri);
            if (uri.contains("/login")) {
                handlerForward(fullRequest, ctx, "请求[登录]服务");
            } else {
                handlerForward(fullRequest, ctx, "请求[其他]服务");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 通过httpclient请求转发
     * @param fullRequest
     * @param ctx
     * @param body
     */
    private void handlerForward(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String body) {
        FullHttpResponse response = null;
        try {
            //发送请求获取数据
            String responseContent = requestByHttp(body);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(responseContent.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
                ctx.flush();
            }
        }
    }

    /**
     * 发送Http请求，通过Client发送
     * @param content
     * @return
     * @throws Exception
     */
    private String requestByHttp(String content) throws Exception{
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("content", content);
        //发送请求
        String responseContent = HttpClientDemo.sendPostByJson(NettyHttpServer.HTTP_ADDR, paramMap.toString());
        return responseContent;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 新增request过滤器
     * @param httpRequestFilter
     * @return
     */
    public HttpHandler addRequestFilter(HttpRequestFilter httpRequestFilter){
        this.requestFilterList.add(httpRequestFilter);
        return this;
    }

    /**
     * 新增response过滤器
     * @param httpResponseFilter
     * @return
     */
    public HttpHandler addResponseFilter(HttpResponseFilter httpResponseFilter){
        this.responseFilterList.add(httpResponseFilter);
        return this;
    }
}
