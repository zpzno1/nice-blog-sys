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
package org.kiwipeach.blog.autoconfig;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author kiwipeach
 * @create 2019-03-30
 */
@Configuration
public class RedisCacheTtlAutoConfig {

    @Configuration
    public class RedisCacheConfig {

        @Bean
        public KeyGenerator simpleKeyGenerator() {
            return (o, method, objects) -> {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(o.getClass().getSimpleName());
                stringBuilder.append(".");
                stringBuilder.append(method.getName());
                stringBuilder.append("[");
                for (Object obj : objects) {
                    stringBuilder.append(obj.toString());
                }
                stringBuilder.append("]");
                return stringBuilder.toString();
            };
        }

        @Bean
        public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
            return new RedisCacheManager(
                    RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                    this.getRedisCacheConfigurationWithTtl(600), // 默认策略，未配置的 key 会使用这个
                    this.getRedisCacheConfigurationMap() // 指定 key 策略
            );
        }

        /**
         * FIXME 考虑如何做成可配置化
         * 获取需要设置ttl的键值
         *
         * @return 返回configMap
         */
        private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
            Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
            //需要特殊设置的缓存配置
            redisCacheConfigurationMap.put("SYSUSER_CACHE", this.getRedisCacheConfigurationWithTtl(300));
            return redisCacheConfigurationMap;
        }

        private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
            //Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
            //ObjectMapper om = new ObjectMapper();
            //om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            //jackson2JsonRedisSerializer.setObjectMapper(om);s
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(seconds));

            return redisCacheConfiguration;
        }
    }
}
