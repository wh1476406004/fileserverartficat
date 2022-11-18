package com.test;


import java.util.List;

/**
 * Description:
 *
 * @author: wangjg
 * Version: 1.0
 * Create Date Time: 2021/8/9 17:06.
 */
public class BiExport {
//    public static void main(String[] args) {
//        String result = doPost("http://120.132.122.134:8080/bi/api?action=login&adminv=admin&passv=Angi6686", "");
//        // 字符串转XML
//        Document document = null;
//        try {
//            document = DocumentHelper.parseText(result);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        // 获取根节点
//        Element rootElt = document.getRootElement();
//        List<Element> bookEles = rootElt.elements();
//        String ret = "";
//        for (Element book : bookEles) {
//            // element()获取子节点指定的子元素
//            Element nameElement = book.element("message");
//            // getText()获取子元素的文本内容  .replaceAll("&","&amp;")
//            ret = nameElement.getText();
//        }
//        try {
//            String postURL = "http://120.132.122.134:8080/bi/api?action=downloadFile&token=" + ret;
//            String rptPath = "侨益bi/经营管理分析/17月度经营分析表/东北组团/01东北组团业务量统计";
//            PostMethod postMethod = null;
//            postMethod = new PostMethod(postURL);
//            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//            //参数设置，需要注意的就是里边不能传NULL，要传空字符串
//            NameValuePair[] data = {
//                    new NameValuePair("xmlData", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                            "<info>\n" +
//                            "<user>admin</user>\n" +
//                            "<rptPath> "+ rptPath + "</rptPath>\n" +
//                            "<fileType>pdf</fileType>\n" +
//                            "</info>")
//            };
//            postMethod.setRequestBody(data);
//
//            HttpClient httpClient = new HttpClient();
//            int response = httpClient.executeMethod(postMethod); // 执行POST方法
//            String path = "E://anji//workSpace";
//            AgFileReader.writeFile(path,postMethod.getResponseBodyAsString());
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public static String doPost(String url, String params) {
//        DefaultHttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost(url);
//        String result = null;
//        try {
//            StringEntity s = new StringEntity(params);
//            s.setContentEncoding("UTF-8");
//            s.setContentType("application/xml");//发送json数据需要设置contentType
//            post.setEntity(s);
//            HttpResponse res = client.execute(post);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                HttpEntity entity = res.getEntity();
//                result = EntityUtils.toString(res.getEntity());// 返回json格式：
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
}


