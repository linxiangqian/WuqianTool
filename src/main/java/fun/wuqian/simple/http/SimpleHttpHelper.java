package fun.wuqian.simple.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author：悟纤「公众号SpringBoot」
 * date：2024/6/29
 */
public class SimpleHttpHelper {

    /**
     * 远程文件是否存在
     * @param fileUrl
     * @return true-存在，false-不存在。
     */
    public static boolean isRemoteFileExists(String fileUrl) {
        if(fileUrl == null) return false;

        try {
            URL url = new URL(fileUrl);
            HttpURLConnection.setFollowRedirects(false);
            //默认情况下，HttpURLConnection的超时时间是无限大，即没有超时限制。这意味着如果网络连接存在问题或服务器响应缓慢，请求可能会一直阻塞，直到连接建立或数据读取完成。
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间为5000毫秒
            httpURLConnection.setConnectTimeout(5000);
            // 设置读取超时时间为10000毫秒
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("HEAD");
            return (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 校验远程文件是否是0字节
     * @param fileUrl
     * @return true:0字节；false：存在并且不是0字节.
     */
    public static boolean checkRemoteFileEmpty(String fileUrl){
        if(fileUrl == null) return true;

        boolean flag = true;
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            // 设置连接超时时间为5000毫秒
            httpURLConnection.setConnectTimeout(5000);
            // 设置读取超时时间为10000毫秒
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                int fileSize = httpURLConnection.getContentLength(); // 获取文件大小
                if (fileSize == 0) {
                    //System.out.println("远程文件是0字节");
                } else {
                    //System.out.println("远程文件不是0字节");
                    flag = false;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            return true;
        }
        return flag;
    }

    public static String getRedirectionLocation(String urlString) {
        if(urlString == null) return null;

        String newUrlString = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            // 设置是否自动处理重定向 false:禁止自动重定向.
            connection.setInstanceFollowRedirects(false);
            // 发送GET请求
            connection.setRequestMethod("GET");
            connection.connect();

            // 获取响应码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //System.out.println("No redirection occurred.");
            } else if (responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
                    responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
                    responseCode == HttpURLConnection.HTTP_SEE_OTHER ||
                    responseCode == 307) {
                // 如果发生了重定向，获取新的Location
                newUrlString = connection.getHeaderField("Location");
                //System.out.println("Redirection occurred. New URL: " + newUrlString);
            } else {
                //System.out.println("Request failed with response code: " + responseCode);
            }
            //String location = connection.getHeaderField("Location");
            //System.out.println("location: "+location);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        if(newUrlString == null){
            newUrlString = urlString;
        }
        return newUrlString;
    }

}
