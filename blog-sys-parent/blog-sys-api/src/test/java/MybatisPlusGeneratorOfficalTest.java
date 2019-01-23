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

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * 官方的生成代码方式
 *
 * new AutoGenerator().setGlobalConfig(
 *     ...
 * ).setDataSource(
 *     ...
 * ).setStrategy(
 *     ...
 * ).setPackageInfo(
 *     ...
 * ).setCfg(
 *     ...
 * ).setTemplate(
 *     ...
 * ).execute();
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/29
 */
public class MybatisPlusGeneratorOfficalTest {

    /**
     * Oracle 代码自动生成方式
     */
    public static void main(String[] args) {
        int result = 1;
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));

        String tables[] = new String[]{"SYS_PERMISSION", "PK_SYS_PERMISSION", "T_BLOG", "PK_T_BLOG", "R_TAG_BLOG", "PK_R_TAG_BLOG", "SYS_PARAM", "SYS_USER", "PK_SYS_USER", "T_BLOG_CATEGORY", "PK_T_BLOG_CATEGORY", "T_BLOG_TAG", "PK_T_BLOG_TAG", "SYS_FUNCTION", "PK_T_COMMENT", "T_COMMENT_MSG", "R_USER_PERMISSION"};


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir("D:\\souce_code\\mine_source\\nice-blog-sys\\blog-sys-parent\\blog-sys-api\\src\\main\\java")//输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(false)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        //.setKotlin(true) 是否生成 kotlin 代码
                        .setAuthor("kiwipeach")
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setEntityName("%s")
                        .setMapperName("%sMapper")
                        .setXmlName("%sMapper")
                        .setServiceName("I%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setControllerName("%sController")
                        .setOpen(false)//生成代码后不自动打开文件所在目录
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.ORACLE)// 数据库类型
                        .setTypeConvert(new OracleTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                                System.out.println("自动检测到数据库类型：" + fieldType);
                                //金额或者敏感数值单位需要精确
                                if (fieldType.contains("NUMBER(")&&fieldType.contains("2)")) {//含有这种格式的识别为金额类型
                                    return DbColumnType.BIG_DECIMAL;
                                    //编码枚举数值类型或者布尔类型使用整形表示
                                } else if (fieldType.contains("NUMBER")&&!fieldType.contains(",")) {
                                    return DbColumnType.INTEGER;
                                }else {
                                    //使用默认的
                                    return super.processTypeConvert(globalConfig, fieldType);
                                }
                            }
                        })
                        .setDriverName("oracle.jdbc.driver.OracleDriver")
                        .setUsername("kiwipeach")
                        .setPassword("kiwipeach")
                        .setUrl("jdbc:oracle:thin:@localhost:1521:ORCL")
        ).setStrategy(
                // 策略配置

        new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        // .setDbColumnUnderline(true)//全局下划线命名
                        .setTablePrefix(new String[]{"t_"})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        //.setInclude(new String[]{"SYS_USER","T_BLOG","R_TAG_BLOG","SYS_PARAM","T_BLOG_CATEGORY","T_BLOG_TAG","T_COMMENT","T_LEAVE_MSG"}) // 需要生成的表
                        .setInclude(tables) // 需要生成的表
                        // .setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"test_id"})
                        .setTableFillList(tableFillList)
                // 自定义 mapper 父类
                // .setSuperMapperClass("com.baomidou.demo.TestMapper")
                // 自定义 service 父类
                // .setSuperServiceClass("com.baomidou.demo.TestService")
                // 自定义 service 实现类父类
                // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                // 自定义 controller 父类
                // .setSuperControllerClass("com.baomidou.demo.TestController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                // .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public SysUser setName(String name) {this.name = name; return this;}
                // .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                // .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        .setModuleName("generator")
                        .setParent("cn.kiwipeach")// 自定义包路径
                        .setController("controller")// 这里是控制器包名，默认 web
                        .setEntity("domain")
                        .setMapper("mapper")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setController("controller")

        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("email", "1099501218@qq.com");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
                        "/generator-templates-custom/mapper.xml" + ((1 == result) ? ".ftl" : ".vm")) {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return "D:\\souce_code\\mine_source\\nice-blog-sys\\blog-sys-parent\\blog-sys-api\\src\\main\\resources\\mapper\\" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                }))
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig()
                        .setXml(null)
                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                        .setController("generator-templates-custom/controller.java")
                        .setEntity("generator-templates-custom/entity.java")
                        .setMapper("generator-templates-custom/mapper.java")
                        .setService("generator-templates-custom/service.java")
                        .setServiceImpl("generator-templates-custom/serviceImpl.java")
        );
        // 执行生成
        if (1 == result) {
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        }
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("email"));
    }
}
