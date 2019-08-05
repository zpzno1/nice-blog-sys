#!/usr/bin/env bash
#jenkins打包成war包之后存放的目录
SOURCE_CODE_PATH="/webapp/nice-blog-sys"
TARGET_JAR_PATH=$SOURCE_CODE_PATH/blog-sys-web/target/NiceBlog.jar
WEB_LOGFILE=/weblogs/nice-blog-sys/nice-blog-sys.out

# 1) 拉取gitee代码
echo "[step 1:]  从gitee上拉取代码"
cd $SOURCE_CODE_PATH
git pull origin master

# 2) mvn编译打包
echo "[step 2:]  用maven进行编译打包"
mvn clean compile package

# 3) springboot服务重启

echo "[step 3:]  重启nice-blog-sys服务"
blog_sys_pid=`ps -ef | grep java| grep $TARGET_JAR_PATH |awk '{print $2}'`

if [ -n "$blog_sys_pid" ]
then
      kill -9 $blog_sys_pid
      echo $blog_sys_pid "nice-blog-sys 进程成功关闭...."
else
      echo "no nice-blog-sys pid alive"
fi
nohup java -Xms512m -Xmx1024m -jar $TARGET_JAR_PATH  --spring.profiles.active=mysql-production ->$WEB_LOGFILE &
echo $blog_sys_pid "nice-blog-sys 进程成功启动...."

echo "[构建执行完毕:]  BUILD AND DEPLOY SUCCESS!"
exit 0;
