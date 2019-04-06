<p align=center>
  <a href="http://www.kiwipeach.cn">
    <img src="https://images.gitee.com/uploads/images/2019/0329/012240_a69e0cc1_1387578.png" alt="nice blog system">
  </a>
</p>
<p align=center>
   Nice Blog System 是一个基于Java开发，“便捷轻巧、高性能、外形Nice”的博客系统
</p>

<p align="center">
  <a href="https://gitee.com/KiWiPeach/nice-blog-sys"><img alt="Build Status" src="https://img.shields.io/hexpm/l/plug.svg"></a>
    <a href="https://gitee.com/KiWiPeach/nice-blog-sys"><img alt="Build Status" src="
https://img.shields.io/shippable/5444c5ecb904a4b21567b0ff.svg"></a>
<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>

</p>
<p align="center">
 <a href="javascript:;">QQ交流群 :679722876</p>
</p>

## 项目说明

 纯Java编写的个人博客系统，力求以`便捷轻巧、高性能、外形Nice`为目标，适用于个人快速搭建博客平台。我不完美，但向往远方。[在线演示](http://www.kiwipeach.cn) 

## 项目亮点

- 最新的技术选型,使用SpirngBoot最新版本(当前2.1.3.RELEASE)，关注我就等于关注最新SpringBoot整合技巧方案，(*^__^*).....
- 项目技术选型[maven，spring-boot,mybatis-plus,shiro,thymeleaf,bootstrap4，jquery....]
- 支持多平台(q、github、gitee)社交账号登陆及本地系统账号登录。
- Shiro做安全权限验证，博客资源链接能做安全过滤，权限管理,对于私密链接有保障。
- 使用了Redis缓存，提升博客网站的性能。
- 博客系统同时支持MySQL和Oracle数据库。
- 独具特色的评论回复功能，增强博客的互动性。
- 采用国内七牛云图床，简单、稳定、易用。
- 持续开源更新，站长亲自撸码，经久不衰的持续更新。


## 安装教程


##### 方式一：jar包部署
```shell
    nohup java -Xms513m -Xmx1024m -jar blog-sys-web.jar --spring.profiles.active=aliyun &
```

##### 方式二：war包部署
```shell
    具体流程略
```
##### 方式三：本地开发

1. 拉取源代码:
```shell
git clone https://gitee.com/KiWiPeach/nice-blog-sys.git
```

2. Maven编译执行
```shell
#注意：oracle驱动包，在中央仓库中未包含，推荐本地安装解决
mvn clean package
```

2. 准备数据库，redis环境,并且修改对应配置文件,具体见配置文件。
```shell
#主要修改配置：数据库连接、缓存Redis、三方登录参数、shiro缓存cookie域名
```


3. 系统启动类
``` shell
nice-blog-sys/blog-sys-web/src/main/java/cn/kiwipeach/blog/BlogWebApplication.java
```

4. 访问页面。



## 系统截图

##### 博客首页

![博客首页内容](http://img.kiwipeach.cn/1f86a039ad9109d9bb175f20d9cb6d5b.png)

##### 博客详情

![博客详情](https://images.gitee.com/uploads/images/2019/0329/012203_59856321_1387578.png)

##### 博客后台一角:

![输入图片说明](https://images.gitee.com/uploads/images/2018/1127/113125_8043f6a4_1387578.png "屏幕截图.png")

##### 发表博文界面：

![输入图片说明](https://images.gitee.com/uploads/images/2018/1209/113059_fa9be492_1387578.png "XSXZ(]B3KJPS6K6[71}S266.png")
