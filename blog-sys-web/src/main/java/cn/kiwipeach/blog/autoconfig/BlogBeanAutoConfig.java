/*
 * Copyright 2019 liuburu@qq.com.
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
package cn.kiwipeach.blog.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 描述：自动装配bean
 *
 * @author kiwipeach
 * @create 2019-03-31
 */
@Configuration
public class BlogBeanAutoConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    //@Bean
    //public RedisTemplate setRedisTemplate() {
    //    RedisSerializer stringSerializer = new StringRedisSerializer();
    //    redisTemplate.setKeySerializer(stringSerializer);
    //    redisTemplate.setValueSerializer(stringSerializer);
    //    redisTemplate.setHashKeySerializer(stringSerializer);
    //    redisTemplate.setHashValueSerializer(stringSerializer);
    //    this.redisTemplate = redisTemplate;
    //    return this.redisTemplate;
    //}

}
