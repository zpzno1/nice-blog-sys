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
package cn.kiwipeach.blog;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.validation.constraints.NotNull;

/**
 * @author kiwipeach
 * @create 2019-02-24
 */
@Slf4j
public class IdWorkderTest {

    @Test
    public void testId() {
        log.info("getIdStr：{}" + IdWorker.getIdStr());
        log.info("getId：{}" + IdWorker.getId());
        log.info("get32UUID：{}" + IdWorker.get32UUID());
    }

    @Test
    public void testNotNull() {
        boolean flagA = true;
        boolean flagB = true;
        boolean flagC = false;
        if (flagA){
            System.out.println("1111111111111111");
        }else if(flagA&&flagB){
            System.out.println("22222222");
        }else {
            System.out.println("3333333333333");
        }

    }

    public String method(String value) {
        return value;
    }
}
