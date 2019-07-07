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

## 项目特点

-   :tw-1f1e6: 大胆的采用了SpringBoot当前最新版本（2.1.3.RELEASE），敢做第一个吃螃蟹的人 。
-   :tw-1f1e7:  时下较为流行的技术，可让你的博客技术栈始终保持子在主流前沿地位[spring-boot,mybatis-plus,shiro,thymeleaf,bootstrap4...]
-   :tw-1f1e8:  支持多社交账号登陆(q、github、gitee)及本地系统账号登录，记录每一个与你交流的伙伴。
-  :tw-1f1e9: Shiro做安全权限验证，博客资源链接能做安全过滤，权限管理,对于私密链接有保障。
-  :tw-1f1ea: Redis应用缓存，简洁严谨的代码逻辑，恰到好处的性能优化，我很专业  。
-  :tw-1f1eb: 博客系统同时支持MySQL和Oracle数据库，敢问还有谁 :joy: 。
-  :tw-1f1ec: 独具特色的评论回复功能，增强博客的互动性。
-  :tw-1f1ed: 采用国内七牛云图床，简单、稳定、易用，解决系统静态资源相应国过慢问题。
-  :tw-1f1ee: 持续开源更新，站长亲自撸码，经久不衰的持续更新，有我在，你还怕不会动手么 :no_mouth: 。
-  :tw-1f1ef: 这是我的服务器配置`1 vCPU 2 GiB 1Mbps`，配置够低了吧？但低配置并不代表你的站点就会是低性能，差体验，而是缺少解决方案和技术。

## 安装教程

##### 步骤一：环境准备

| 环境依赖                 | 描述                          |
| ------------------------ | ----------------------------- |
| 数据库                   | MySQL或者Oracle，二选一       |
| Java Runtime Environment | 推荐使用jdk1.8及1.8以上的版本 |
| Redis                    | 应用缓存，缓解数据库访问压力  |

##### 步骤二：修改配置

```yaml
#数据库配置
spirng
    datasource:
    username: <?>
    password: <?>
    url: <?>
    ....
    
#Redis配置
    cache:
    type: redis
    redis:
    host: <?>
    password: <?>
    ......	
#三方登录配置(qq,github.gitee)
qq:
  client:
    app_ID: <?>
    app_KEY: <?>
    ......
github:
  client:
    clientId: <?>
    clientSecret: <?>
    ......
gitee:
  client:
    clientId: <?>
    clientSecret: <?>
    ......
    
#千牛图床配置
qiniu:
  access-key: <?>
  private-key: <?>
  ......
```
##### 步骤四：运行程序

```shell
#本地运行
nice-blog-sys/blog-sys-web/src/main/java/cn/kiwipeach/blog/BlogWebApplication.java

#jar包运行
nohup java -Xms512m -Xmx1024m -jar /webapp/NiceBlog.jar  --spring.profiles.active=mysql-production ->/weblogs/nice-blog-sys/nice-blog-sys.out &
```

## 系统运行截图

##### 博客首页

![博客首页内容](./docs/preview/博客首页.png)

##### 博客详情

![博客首页内容](./docs/preview/博客详情.png)

##### 博客评论回复

![博客首页内容](./docs/preview/博客评论回复.png)

##### 博客点赞

![博客首页内容](./docs/preview/博客点赞.jpg)

##### 博客归档

![博客首页内容](./docs/preview/博客归档.png)

##### 博客关于我

![博客首页内容](./docs/preview/博客关于.png)

##### 博客后台(待开发):

![输入图片说明](https://images.gitee.com/uploads/images/2018/1127/113125_8043f6a4_1387578.png "屏幕截图.png")

##### 发表博文界面(待开发)：

![输入图片说明](https://images.gitee.com/uploads/images/2018/1209/113059_fa9be492_1387578.png "XSXZ(]B3KJPS6K6[71}S266.png")
