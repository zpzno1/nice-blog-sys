package cn.kiwipeach.blog.qiniu.official;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;


public class BatchDemo {

    public static void main(String args[]) {
        //设置需要操作的账号的AK和SK
        String ACCESS_KEY = "Access_Key";
        String SECRET_KEY = "Secret_Key";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);

        //创建Batch类型的operations对象
        BucketManager.BatchOperations operations = new BucketManager.BatchOperations();

        //第一组源空间名、原文件名，目的空间名、目的文件名
        String bucketFrom1 = "yourbucket";
        String keyFrom1 = "srckey1";
        String bucketTo1 = "yourbucket";
        String keyTo1 = "destkey1";

        //第二组源空间名、原文件名，目的空间名、目的文件名
        String bucketFrom2 = "yourbucket";
        String keyFrom2 = "srckey2";
        String bucketTo2 = "yourbucket";
        String keyTo2 = "destkey2";


        try {
            //调用批量操作的batch方法
            Response res = bucketManager.batch(operations.addMoveOp(bucketFrom1, keyFrom1, bucketTo1, keyTo1)
                    .addMoveOp(bucketFrom2, keyFrom2, bucketTo2, keyTo2));

            System.out.println(res.toString());

        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }
}
