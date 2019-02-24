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
package cn.kiwipeach.blog.store;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 七牛云配置bean
 *
 * @author kiwipeach
 * @create 2019-02-23
 */
@Component
@ConfigurationProperties("qiniu")
public class QiNiuConfigProperties {
    /**
     * 千牛AK
     */
    private String accessKey;
    /**
     * 千牛PK
     */
    private String privateKey;
    /**
     * image桶，博客
     */
    private String imageBucket;
    /**
     * image桶的千牛域名
     */
    private String imageDomain;
    /**
     * markdown桶，博客
     */
    private String markdownBucket;
    /**
     * markdown桶的千牛域名
     */
    private String markdownDomain;
    /**
     * 资源过期时间，单位/秒
     */
    private long expireTime;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getImageBucket() {
        return imageBucket;
    }

    public void setImageBucket(String imageBucket) {
        this.imageBucket = imageBucket;
    }

    public String getImageDomain() {
        return imageDomain;
    }

    public void setImageDomain(String imageDomain) {
        this.imageDomain = imageDomain;
    }

    public String getMarkdownDomain() {
        return markdownDomain;
    }

    public void setMarkdownDomain(String markdownDomain) {
        this.markdownDomain = markdownDomain;
    }

    public String getMarkdownBucket() {
        return markdownBucket;
    }

    public void setMarkdownBucket(String markdownBucket) {
        this.markdownBucket = markdownBucket;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
