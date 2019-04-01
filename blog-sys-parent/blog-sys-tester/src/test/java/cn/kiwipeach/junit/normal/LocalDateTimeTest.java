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
package cn.kiwipeach.junit.normal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * 描述：时间获取测试
 *
 * @author kiwipeach
 * @create 2019-04-01
 */
@Slf4j
public class LocalDateTimeTest {


    @Test
    public void 获取格式化时间() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        log.info("{}年{}月{}日 {}时{}分{}秒 星期{}", now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getDayOfWeek());
        TextStyle style = TextStyle.SHORT;
        Locale locale = Locale.CHINA;
        String format = String.format("%s年%s%s日 %s时%s分%s秒 %s", now.getYear(), now.getMonth().getValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getDayOfWeek().getDisplayName(style,locale));
        log.info(format);
    }

}
