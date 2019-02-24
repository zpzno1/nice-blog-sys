package cn.kiwipeach.blog.qiniu.mine;

import com.qiniu.util.Auth;
import org.junit.Test;

public class DownloadDemo {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "egP7QEuBIF-kN03VuxuUeqAYtfylyZR5h9VSTM8h";
    String SECRET_KEY = "XEbTW-3zyO26deFxW0_PBt79bNGH6IhQcji59LLB";
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //构造私有空间的需要生成的下载的链接
    //String URL = "http://bucketdomain/key";
    String URL = "http://pmw84t3zh.bkt.clouddn.com/hello-private-bucket.jpg";


    @Test
    public void privateDownLoad() {
        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        //String downloadRUL = auth.privateDownloadUrl(URL, 3600);
        String downloadRUL = auth.privateDownloadUrl(URL, 10);
        System.out.println(downloadRUL);
        //如果在10s内没有查看图片，那么token将被销毁；否则可以一直查看。
        //eg: http://pmw84t3zh.bkt.clouddn.com/hello-private-bucket.jpg?e=1550912314&token=egP7QEuBIF-kN03VuxuUeqAYtfylyZR5h9VSTM8h:ScudlLHx5-qJkA77u2ua1IJUU0c=
    }

    @Test
    public void publicDownloadUrl(){

    }
}