package com.geek.homework.week02.nio;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 使用httpclient调用HttpServer01
 */
public class HttpClientDemo {

    public static void main(String[] args) throws IOException {
        String postByJson = sendPostByJson("http://www.baidu.com", "");
        System.out.println(postByJson);
    }

    /**
     * 通过post方式调用http接口
     *
     * @param url       url路径
     * @param jsonParam json格式的参数
     * @return
     * @throws Exception
     */
    public static String sendPostByJson(String url, String jsonParam) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClients.createDefault();
            // 设置请求头和报文
            HttpPost httpPost = new HttpPost(url);
            Header header = new BasicHeader("Accept-Encoding", null);
            httpPost.setHeader(header);
            // 设置报文和通讯格式
            StringEntity stringEntity = new StringEntity(jsonParam, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
