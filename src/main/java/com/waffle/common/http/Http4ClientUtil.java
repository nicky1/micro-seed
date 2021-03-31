package com.waffle.common.http;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by yixiaoshuang on 2016/9/28.
 * http工具类，基于httpcomponents4.5 实现
 */
@Slf4j
public class Http4ClientUtil {

    static final int timeOut = 10 * 1000;

    private static CloseableHttpClient httpClient = null;

    private final static Object syncLock = new Object();

    private static SSLContext ctx = null;

    private static HostnameVerifier verifier = null;

    private static class DefaultTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }

    static {

        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());

            ctx.getClientSessionContext().setSessionTimeout(15);
            ctx.getClientSessionContext().setSessionCacheSize(1000);

        } catch (Exception e) {

        }

        verifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

    }

    private static void initConfig(HttpRequestBase httpRequestBase) {
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * 获取HttpClient对象
     *
     * @return
     */
    public static CloseableHttpClient getHttpClient(String url) {
        String hostname = url.split("/")[2];
        int port = 80;
        if (hostname.contains(":")) {
            String[] arr = hostname.split(":");
            hostname = arr[0];
            port = Integer.parseInt(arr[1]);
        }
        if (httpClient == null) {
            synchronized (syncLock) {
                if (httpClient == null) {
                    httpClient = createHttpClient(200, 40, 100, hostname, port, url);
                }
            }
        }
        return httpClient;
    }

    private static LayeredConnectionSocketFactory getSslFactory() {
        return new SSLConnectionSocketFactory(ctx, verifier);
    }

    /**
     * 创建HttpClient对象
     *
     * @return
     */
    public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute, String hostname,
                                                       int port, final String url) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf).register("https", getSslFactory()).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(maxPerRoute);
        HttpHost httpHost = new HttpHost(hostname, port);
        // 将目标主机的最大连接数增加
        cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                log.info("exception:{},url:{},executionCount:{}", exception, url, executionCount);
                if (executionCount >= 3) {// 如果已经重试了3次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return true;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return true;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).build();

        return httpClient;
    }

    /**
     * POST请求URL获取内容
     *
     * @param url
     * @param :json格式
     * @return
     * @throws IOException
     * @author SHANHY
     * @create 2015年12月18日
     */
    public static String postJson(String url, String params) throws IOException {
        return post(url, params, ContentType.APPLICATION_JSON);
    }

    public static String postForm(String url, String params) {
        return post(url, params, ContentType.APPLICATION_FORM_URLENCODED);
    }

    public static String postXml(String url, String xmlStr) {
        log.info("请求参数:{}", xmlStr);
        HttpPost httppost = new HttpPost(url);
        initConfig(httppost);
        CloseableHttpResponse response = null;
        try {
            log.info("url:{}", url);

            HttpEntity httpEntity = new StringEntity(xmlStr, "utf-8");
            httppost.setEntity(httpEntity);
            response = getHttpClient(url).execute(httppost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            log.error("postXml", e);
            throw new RuntimeException(e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public static String get(String url) {
        return get(url, null);
    }

    /**
     * GET请求URL获取内容
     *
     * @param url
     * @return
     * @author SHANHY
     * @create 2015年12月18日
     */
    public static String get(String url, Map<String, String> params) {

        CloseableHttpResponse response = null;
        try {

            StringBuilder builder = new StringBuilder();
            builder.append(url);
            if (null != params && params.size() > 0) {
                builder.append("?");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                for (String key : params.keySet()) {
                    nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
                }
                Joiner.on("&").appendTo(builder, nameValuePairs);
            }

            HttpGet httpget = new HttpGet(builder.toString());
            initConfig(httpget);

            response = getHttpClient(builder.toString()).execute(httpget, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            log.error("异常:{}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                log.error("异常:{}", e);
            }
        }
        return null;
    }

    public static String post(String url, String params, ContentType contentType) {
        HttpPost httppost = new HttpPost(url);
        initConfig(httppost);
        CloseableHttpResponse response = null;
        try {
            log.info("url:{}", url);

            HttpEntity httpEntity = new StringEntity(params, contentType);
            httppost.setEntity(httpEntity);
            response = getHttpClient(url).execute(httppost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            log.error("post异常, url=" + url + " exception:", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                log.error("异常:{}", e);
            }
        }
        return null;
    }


}
