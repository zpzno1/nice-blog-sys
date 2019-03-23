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
package cn.kiwipeach.blog.quartz;

import cn.hutool.cron.CronUtil;
import org.junit.Test;

/**
 * 运行测试
 *
 * @author kiwipeach
 * @create 2019-03-22
 */
public class RunTest {

    @Test
    public void test() throws InterruptedException {
        //开启秒级别配置
        CronUtil.setMatchSecond(true);
        CronUtil.start();
        Thread.sleep(100000L);
    }
}
