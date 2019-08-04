#!/usr/bin/env bash
#jenkins打包成war包之后存放的目录
SOURCE_CODE_PATH="/webapp/nice-blog-sys/blog-sys-web"
TARGET_JAR_PATH=$SOURCE_CODE_PATH/target/NiceBlog.jar
WEB_LOGFILE=/weblogs/nice-blog-sys/nice-blog-sys.out

#需要的环境变量：SOURCE_CODE_PATH(项目源代码路径)
# 1) 拉取gitee代码
echo "[step 1:]  从gitee上拉取代码"
cd $SOURCE_CODE_PATH
git pull origin master

# 2) mvn编译打包
echo "[step 2:]  用maven进行编译打包"
mvn clean compile package

# 3) springboot服务重启

echo "[step 3:]  重启springboot进城服务"
blog_sys_pid=`ps -ef | grep java| grep $TARGET_JAR_PATH |awk '{print $2}'`

if [ -n "$blog_sys_pid" ]
then
      kill -9 $blog_sys_pid
      echo $blog_sys_pid "nice-blog-sys process is killed success...."
else
      echo "no nice-blog-sys pid alive"
fi
nohup java -Xms512m -Xmx1024m -jar $TARGET_JAR_PATH  --spring.profiles.active=mysql-production ->$WEB_LOGFILE &
echo "[构建结果:]  BUILD SUCCESS!"

