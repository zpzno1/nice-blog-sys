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
package cn.kiwipeach.junit.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试logback日志打印级别
 *
 * @author kiwipeach
 * @create 2019-03-24
 */
//@Slf4j
public class 日志打印级别测试 {

    private Logger log = LoggerFactory.getLogger(日志打印级别测试.class);

    @Test
    public void 五个日志级别测试() {
        System.out.println("测试日志System.out");
        log.trace("Trace Level");
        log.debug("Debug Level");
        log.info("Info Level");
        log.warn("Warn Level");
        log.error("ErrorLevel");
    }
}
