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
package cn.kiwipeach.junit.ehcahe;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author kiwipeach
 * @create 2019-03-24
 */
@Slf4j
public class HellwoldEhcacheDemo {

    @Test
    public void 创建缓存管理器方式() {
        //默认方式：默认加载类路径下面的ehcache.xml
        CacheManager cacheManager1 = CacheManager.newInstance();
        log.warn("CacheManager1中的Cache名称：{}", cacheManager1.getCacheNames());
        //指定文件名：
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("ehcache-demo.xml");
        CacheManager cacheManager2 = CacheManager.create(resourceAsStream);
        log.warn("CacheManager2中的Cache名称：{}", cacheManager2.getCacheNames());
    }

    @Test
    public void 获取已配置缓存的信息名称() {

        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
        CacheManager cacheManager = CacheManager.create(resourceAsStream);
        String[] cacheNames = cacheManager.getInstance().getCacheNames();
        System.out.println("配置的缓存名称：");
        for (String cacheName : cacheNames) {
            System.out.println("cacheName:" + cacheName);
        }
    }

    @Test
    public void Ehcache缓存使用() throws FileNotFoundException {
        // 1. 创建缓存管理器
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
        CacheManager cacheManager = CacheManager.create(resourceAsStream);
        String[] cacheNames = cacheManager.getCacheNames();


        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("HelloWordCache");

        // 3. 创建元素
        Element element = new Element("key1", "value1");

        // 4. 将元素添加到缓存
        cache.put(element);

        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        // 6. 删除元素
        cache.remove("key1");

        //Dog dog = new Dog("1", "taidi","2");
        //Element element2 = new Element("taidi", dog);
        //cache.put(element2);
        //Element value2 = cache.get("taidi");
        //Dog dog2 = (Dog) value2.getObjectValue();
        //System.out.println(dog2);

        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }

    @Test
    public void 属性测试maxElementsInMemory() {

    }

}
