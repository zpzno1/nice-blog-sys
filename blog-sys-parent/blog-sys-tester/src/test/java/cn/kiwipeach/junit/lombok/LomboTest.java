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
package cn.kiwipeach.junit.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 测试lombok的getter和setter
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/08
 */
@Getter
@Setter
public class LomboTest {
    private Integer id;
    private String name;
    private BigDecimal salary;
    private Date birthday;
}

//getter、setter、equals、canEqual、hashCode、toString
@Data
//@ToString(exclude = {"id","sal"})
//排除成员变量名
@ToString(includeFieldNames = false)
class DataBean1 {
    @NonNull
    private Integer id;
    private String name;
    private BigDecimal salary;
    private Date birthday;
}

@NoArgsConstructor
@AllArgsConstructor
class DataBean2 {
    private Integer id;
    private String name;
    private BigDecimal salary;
    private Date birthday;
}

@Slf4j
class LogBean{
    public void logShow(){
        log.info("test");
    }
}


