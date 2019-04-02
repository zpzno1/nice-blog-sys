package cn.kiwipeach.junit.qiniu.mine;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

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
            File file = new File("C:\\Users\\kiwipeach\\Desktop\\临时目录\\interface.md");
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
            String FilePath = "C:\\Users\\kiwipeach\\Pictures\\美图\\10.jpg";
            //String FilePath = "D:\\souce_code\\mine_source\\nice-blog-sys\\开发语录.md";
            File file = new File(FilePath);
            //调用put方法上传
            Response res = uploadManager.put(FilePath, file.getName(), getUpToken());
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
     * 测试公有七牛云空间上传
     */
    @Test
    public void testPublicUploadByInputStream() throws FileNotFoundException {
        selectBucketname("kiwipeach-bucket");
        try {
            String filePath = "C:\\Users\\kiwipeach\\Pictures\\美图\\10.jpg";
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            //调用put方法上传
            Response res = uploadManager.put(fileInputStream, "inputstream-demo.jpg", getUpToken(),null,"application/octet-stream");
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
     * 测试公有七牛云上传md博客测试数据文件
     */
    @Test
    public void testPublicExampleDataUpload() {
        selectBucketname("kiwipeach-bucket");
        File file = new File("D:\\souce_code\\mine_source\\nice-blog-sys\\project_files\\examples");
        File[] files = file.listFiles();
        for (File f:files){
            try {
                //调用put方法上传
                Response res = uploadManager.put(f, f.getName(), getUpToken());
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
         * 00:18:25.627 [main] INFO cn.kiwipeach.blog.qiniu.mine.UploadDemo - 上传结果：{"hash":"FtGvZPuHWqL-vJ-e7Zc-17r6R6S3","key":"Github相关语法手册.md"}
         * 00:18:28.256 [main] INFO cn.kiwipeach.blog.qiniu.mine.UploadDemo - 上传结果：{"hash":"FofundtYWnkLPxcDiOzfRdSYk1eP","key":"Linux实用命令手册.md"}
         * 00:18:28.691 [main] INFO cn.kiwipeach.blog.qiniu.mine.UploadDemo - 上传结果：{"hash":"Ft6jGcV5-d-5lzFcY8TUbXE13unm","key":"Markdown语法简介.md"}
         * 00:18:28.986 [main] INFO cn.kiwipeach.blog.qiniu.mine.UploadDemo - 上传结果：{"hash":"Ft6G4eLC2Gnu-OIsQbUGJz1C153W","key":"SSR搭建教程.md"}
         * 00:18:29.390 [main] INFO cn.kiwipeach.blog.qiniu.mine.UploadDemo - 上传结果：{"hash":"Fkc3SYNnXNOejuFkH0dzyuV6TsYt","key":"windows双网卡设置.md"}
         * 00:18:30.007 [main] INFO cn.kiwipeach.blog.qiniu.mine.UploadDemo - 上传结果：{"hash":"Fu4PSdBi8_tGcgWohd8UFstruNB1","key":"七牛云-关于回调流程.md"}
         * 查看图片方式：http://cdn.kiwipeach.cn/Github相关语法手册.md
         */

    }

    @Test
    public void testDownload() {
        String url = "http://pmw84t3zh.bkt.clouddn.com/开发语录.md";
        //String downloadRUL = auth.privateDownloadUrl(url, 3600);
        String downloadRUL = auth.privateDownloadUrl(url, 10);
        System.out.println(downloadRUL);
        //http://pmw84t3zh.bkt.clouddn.com/开发语录.md?e=1550165631&token=egP7QEuBIF-kN03VuxuUeqAYtfylyZR5h9VSTM8h:29GxUAK8OhOUPYWdeJ-1IxYnPV0=
        //http://pmw84t3zh.bkt.clouddn.com/开发语录.md?e=1550165661&token=egP7QEuBIF-kN03VuxuUeqAYtfylyZR5h9VSTM8h:IHniq6j20b8SvGP08n60devWm0I=
    }


}
