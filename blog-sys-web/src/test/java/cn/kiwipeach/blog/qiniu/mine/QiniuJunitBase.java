/*
 * Copyright 2019 kiwipeach(1099501218@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.blog.qiniu.mine;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 七牛通用配置（公测时）
 *
 * @author kiwipeach
 * @create 2019-02-14
 */
public abstract class QiniuJunitBase {
    /**
     * 鉴权ACCESS_KEY
     */
    private static final String ACCESS_KEY = "egP7QEuBIF-kN03VuxuUeqAYtfylyZR5h9VSTM8h";
    /**
     * 鉴权SECRET_KEY
     */
    private static final String SECRET_KEY = "XEbTW-3zyO26deFxW0_PBt79bNGH6IhQcji59LLB";
    /**
     * 要上传的空间
     */
    protected String bucketname = "default_bucket_name";
    /**
     * 上传对象
     */
    protected UploadManager uploadManager;
    /**
     * 密钥配置
     */
    protected Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    /**
     * 默认构造
     */
    public QiniuJunitBase() {
        //自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
        Configuration c = new Configuration(Zone.autoZone());
        //初始化上传对象
        uploadManager = new UploadManager(c);
    }

    public QiniuJunitBase(String bucketname) {
        this();
        this.bucketname = bucketname;
    }

    /**
     * 设置上传桶名称
     *
     * @param bucketname 桶名称
     */
    public abstract void selectBucketname(String bucketname);

    /**
     * 私有空间需要鉴权上传，公共空间不需要
     *
     * @return 返回token字符串
     */
    public String getUpToken() {

        return auth.uploadToken(bucketname);
    }

}
