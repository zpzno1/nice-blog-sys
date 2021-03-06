/*
 * Copyright 2018 kiwipeach.
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
package cn.kiwipeach.junit.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 博客日志级别测试
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/01
 */
@Slf4j
public class BlogLogLevelTest {

    private final static Logger logger = LoggerFactory.getLogger(BlogLogLevelTest.class);

    /**
     * 测试1：logback日志级别
     */
    @Test
    public void testLogLevel() {
        //springboot默认输出是info级别
        logger.trace("Junit trace 日志:{}","kiwipeach");
        logger.debug("Junit debug 日志");
        logger.info("Junit info 日志");
        logger.warn("Junit warn 日志");
        logger.error("Junit error 日志");
    }

    @Test
    public void testLogLevel1() {
        //springboot默认输出是info级别
        log.trace("Junit trace {}日志", "lambook");
        log.debug("Junit debug {}日志", "lambook");
        log.info("Junit info {}日志", "lambook");
        log.warn("Junit warn 日志");
        log.error("Junit error 日志");
    }

}
