package com.geek.homework.week02.nio;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 使用httpclient调用HttpServer01
 */
public class HttpClientDemo {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            // 创建 httpclient 客户端
            httpClient = HttpClients.createDefault();
            // 声明 post 请求
//            HttpPost httpPost = new HttpPost("http://127.0.0.1:8801");
            HttpPost httpPost = new HttpPost("http://www.baidu.com");
            // 设置长连接
            httpPost.setHeader("Connection", "keep-alive");
            // 发起请求获取响应结果
            httpResponse = httpClient.execute(httpPost);

            String result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("获取响应结果" + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放链接
            if (httpClient != null) {
                httpClient.close();
            }
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }



}
