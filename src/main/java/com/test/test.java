//package com.test;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.security.cert.X509Certificate;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @description:
// * @author: GooGoo
// * @time: 2021/8/31 23:25
// */
//public class test {
//    /**
//     * 获取请求国贸接口的token
//     *
//     * @return token
//     */
//    public String getGmToken() {
//        JSONObject json = new JSONObject();
//        json.put("appId", "");
//        json.put("accessKey", "");
//        String result;
//        try {
////            result = HttpRequest. post(GmInterface.tokenUrl)
////                                        .header(Header.CONTENT_TYPE, "application/json")//头信息
////                                        .header("appId",GmInterface.appId)
////                                        .header("signVal",getGmSign())
////                                        .header("timestamp",String.valueOf(System.currentTimeMillis()))
////                                        .body(json.toJSONString())
////                                        .timeout(10000)//超时，毫秒
////                                        .execute().body();
//            HashMap<String, String> objectObjectHashMap = new HashMap<>();
//            objectObjectHashMap.put("Content-Type", "application/json");
//            objectObjectHashMap.put("appId", "");
//            objectObjectHashMap.put("signVal", "");
//            objectObjectHashMap.put("timestamp", System.currentTimeMillis() + "");
//            result = doHttpsPost("https://scc.itg.com.cn/openapi/token/gen", json.toJSONString(), objectObjectHashMap);
////            JSONObject resultInfo = JSON.parseObject(result);
////            if(resultInfo.getBoolean("success")) {
////                //更新数据库的token
////                SParam sParam = new SParam();
////                sParam.setParamValue(resultInfo.getString("data"));
////                sParam.setId("004DA69GAO213d");
////                sParamMapper.updateById(sParam);
////                result =  resultInfo.getString("data");
////            }else{
////                result = null;
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result = null;
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        test test = new test();
//        String gmToken = test.getGmToken();
//        System.out.println(gmToken);
//
//    }
//
//
//    public static String doHttpsPost(String url, String requestSoap, Map<String, String> headers) throws Exception {
//
//        String result = "";
////        try {
//        //int timeOut = 0;
//        CloseableHttpClient closeableHttpClient = createHttpsClient();
//        // 建立HttpPost对象
//        //PostMethod postMethod = new PostMethod(url);
//        HttpPost httppost = new HttpPost(url);
//        byte[] b = requestSoap.getBytes(StandardCharsets.UTF_8);
//        InputStream is = new ByteArrayInputStream(b, 0, b.length);
//        HttpEntity httpEntity = new InputStreamEntity(is, b.length);
//        httppost.setEntity(httpEntity);
//        //设置头信息
//        if (!headers.isEmpty()) {
//            for (String key : headers.keySet()) {
//                if (!key.equals("Content-Type")) {
//                    httppost.setHeader(key, headers.get("key"));
//                }
//            }
//        }
//        httppost.addHeader("Content-Type", headers.get("Content-Type"));
//
//        //发送Post,并返回一个HttpResponse对象
//        //log.error("发送数据::::::::::::::::");
//        HttpResponse httpResponse = closeableHttpClient.execute(httppost);
//        HttpEntity httpEntity2 = httpResponse.getEntity();
//        // 如果状态码为200,就是正常返回
//        int rspCode = httpResponse.getStatusLine().getStatusCode();
//        if (rspCode == org.apache.http.HttpStatus.SC_OK) {
//            result = EntityUtils.toString(httpEntity2);
//                /*log.error("返回结果1::::::::::::::::");
//                log.error(result);*/
//            //log.error("乱码转换::::::::::::::::");
//            //log.error(new String(result.getBytes("iso8859-1"), "UTF-8"));
//            // 得到返回的字符串
//            //log.error("返回结果::::::::::::::::");
//            //log.error(result);
//        } else {
//            result = EntityUtils.toString(httpEntity2);
//            // 得到返回的字符串
//        }
//        //关闭连接
//        closeableHttpClient.close();
////        } catch (Exception e) {
////            throw new RuntimeException("请求接口出错!请求数据["+requestSoap+"]");
////        }
//        return result.replaceAll("&quot;", "\"");
//    }
//
//    /**
//     * 处理https请求安全证书问题（默认接受所有证书）
//     *
//     * @return
//     * @throws Exception
//     */
//    public static CloseableHttpClient createHttpsClient() throws Exception {
//        X509TrustManager x509mgr = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] xcs, String string) {
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] xcs, String string) {
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        };
//// 设置超时时间: 读取超时,连接超时,请求超时时间
//        RequestConfig defaultRequestConfig = RequestConfig.custom()
//                .setSocketTimeout(60000).setConnectTimeout(3000)
//                .setConnectionRequestTimeout(3000)
//                .setStaleConnectionCheckEnabled(false).build();
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, new TrustManager[]{x509mgr},
//                new java.security.SecureRandom());
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslContext,
//                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        return HttpClients.custom().setSSLSocketFactory(sslsf)
//                .setDefaultRequestConfig(defaultRequestConfig).build();
//    }
//}
