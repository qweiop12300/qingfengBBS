package com.chenbaolu.baselib;

/**
 * 描述 :信任ssl
 * 创建时间 : 2022/9/8 17:06
 * 作者 : 23128
 */
import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.ConnectInterceptor;

import javax.net.ssl.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    public static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    /**
     * 默认-不信任自建ssl
     */


    /**
     * 信任所有https-ssl证书
     * 航信https-ssl证书是自建的(无耻，不舍得花钱购买)
     * @return
     */
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder
                    .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                    .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            request = request.newBuilder().addHeader("Authorization",BaseApplication.getToken()).build();
                            Response response = chain.proceed(request);

                            String token = response.header("up-Authorization","");
                            if (!token.equals("")){
                                BaseApplication.setToken(token);
                                request = request.newBuilder().addHeader("Authorization",token).build();
                                response.close();
                                response = chain.proceed(request);
                            }
                            token = response.header("set-Authorization","");
                            if(!token.equals(""))BaseApplication.setToken(token);
                            String userId = response.header("set-UserId","");
                            if (!userId.equals(""))BaseApplication.setUserId(Long.valueOf(userId));
                            return response;
                        }
                    })
                    .build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
