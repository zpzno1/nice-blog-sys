package cn.kiwipeach.blog.qiniu.mine;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;

@Slf4j
public class UploadDemo extends QiniuJunitBase {

    @Override
    public void selectBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    /**
     * 测试私有七牛云空间上传:文件方式
     */
    @Test
    public void testPrivateUpload() {
        selectBucketname("private-bucket");
        try {
            File file = new File("D:\\souce_code\\mine_source\\nice-blog-sys\\开发语录.md");
            //调用put方法上传
            Response res = uploadManager.put(file, file.getName(), getUpToken());
            //Response res = uploadManager.put(new File(FilePath), key, getUpToken());
            log.info("上传结果：{}", res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.info("上传异常信息：{}", r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    /**
     * 测试私有七牛云空间上传:字节形式
     */
    @Test
    public void testPrivateUpload1() {
        selectBucketname("private-bucket");
        try {
            //String FilePath = "C:\\Users\\kiwipeach\\Pictures\\美图\\6.jpg";
            //调用put方法上传
            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            Response res = uploadManager.put(uploadBytes, null, getUpToken());
            log.info("上传结果：{}", res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.info("上传异常信息：{}", r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试公有七牛云空间上传
     */
    @Test
    public void testPublicUpload() {
        selectBucketname("kiwipeach-bucket");
        try {
            String key = "hello-public-bucket-2.jpg";
            String FilePath = "C:\\Users\\kiwipeach\\Pictures\\美图\\6.jpg";
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            log.info("上传结果：{}", res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.info("上传异常信息：{}", r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    @Test
    public void testDownload(){
        String url = "http://pmw84t3zh.bkt.clouddn.com/开发语录.md";
        //String downloadRUL = auth.privateDownloadUrl(url, 3600);
        String downloadRUL = auth.privateDownloadUrl(url, 10);
        System.out.println(downloadRUL);
        //http://pmw84t3zh.bkt.clouddn.com/开发语录.md?e=1550165631&token=egP7QEuBIF-kN03VuxuUeqAYtfylyZR5h9VSTM8h:29GxUAK8OhOUPYWdeJ-1IxYnPV0=
        //http://pmw84t3zh.bkt.clouddn.com/开发语录.md?e=1550165661&token=egP7QEuBIF-kN03VuxuUeqAYtfylyZR5h9VSTM8h:IHniq6j20b8SvGP08n60devWm0I=
    }


}
