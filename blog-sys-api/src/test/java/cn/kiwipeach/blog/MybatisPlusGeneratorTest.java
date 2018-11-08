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
package cn.kiwipeach.blog;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
/**
 * 代码自动生成器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/29
 */
public class MybatisPlusGeneratorTest {


    @Test
    public void generateCode() {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("kiwipeach") // 作者
                .setOutputDir("E:\\Gitee\\201803Blog\\kiwipeach-blog-sys\\blog-sys-web\\src\\main\\java") // 生成路径
                .setFileOverride(true)// 是否覆盖文件
                .setServiceName("I%sService")
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true);// XML columList


        //2. 数据源配置
        DataSourceConfig  dsConfig  = new DataSourceConfig();
        dsConfig.setDbType(DbType.ORACLE)  // 设置数据库类型
                .setDriverName("oracle.jdbc.driver.OracleDriver")
                .setUrl("jdbc:oracle:thin:@localhost:1521:ORCL")
                .setUsername("kiwipeach")
                .setPassword("kiwipeach");

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setTablePrefix("T_")
                .setInclude("T_USER");  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("cn.kiwipeach.blog")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("domain")
                .setXml("mapper");

        //5. 整合配置
        AutoGenerator  ag = new AutoGenerator();

        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }


}
