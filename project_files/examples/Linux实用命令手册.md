# 1.Linux初始换工具安装

```shell
yum install vim wget -y
```



# 2.实用命令

```shell
#查看操作系统的发型版本号
uname -r
#查看当前操作系统信息
cat /etc/redhat-release
#文件解压缩
tar -zxvf nexus-2.14.5-02-bundle.tar.gz -C /usr/local/
#设置防火墙，开放端口
service iptables stop
iptables -A INPUT -p tcp --sport 8081 -j ACCEPT
service iptables save
service iptables status
iptables -L -n
#目录操作
mkdir -p tomcat/weblogs
rm -rf tomcat
#文件操作
touch readme.txt
ifconfig>ip.conf
#服务启停
/etc/init.d/network restar    <==>   service network restart
#系统重启
shutdown -r now 
#文件系统 容量 已用 可用 已用% 挂载点
-bash-4.1$ df -hl
Filesystem            Size  Used Avail Use% Mounted on
/dev/mapper/vg_system-lv_root
                       30G  4.5G   24G  16% /
tmpfs                  16G   72K   16G   1% /dev/shm
/dev/mapper/vg_system-lv_app
                      138G  6.7G  125G   6% /app
/dev/sda1             239M   82M  144M  37% /boot
/dev/mapper/vg_system-lv_oracle
                      9.8G  9.3G   17M 100% /oracle
                      
-bash-4.1$ df
-bash-4.1$ df -hl
Filesystem            Size  Used Avail Use% Mounted on
/dev/mapper/vg_system-lv_root
                       30G  4.5G   24G  16% /
tmpfs                  16G   72K   16G   1% /dev/shm
/dev/mapper/vg_system-lv_app
                      138G  6.8G  124G   6% /app
/dev/sda1             239M   82M  144M  37% /boot
/dev/mapper/vg_system-lv_oracle
                      9.8G  4.1G  5.2G  44% /oracle

#Linux系统时间修改，比本机操作系统快8小时问题解决
date
date -s 
ntpdate 120.24.81.91

#快速执行历史命令
history
!883



```



# 3.Linux一些重要的系统配置文件

```shell
#语言字符集
vim /etc/locale.conf   					
#环境变量
vim /etc/profile       					
#防火墙配置文件
vim /etc/sysconfig/iptables 			
```

# 4.Linux中服务启停

| 任务                 | 旧指令                       | 新指令                             |
| -------------------- | ---------------------------- | ---------------------------------- |
| 使某服务自动启动     | chkconfig –level 3 httpd on  | systemctl enable httpd.service     |
| 使某服务不自动启动   | chkconfig –level 3 httpd off | systemctl disable httpd.service    |
| 检查服务状态         | service httpd status         | systemctl status httpd.service     |
| 显示所有已启动的服务 | chkconfig –list              | systemctl list-units –type=service |
| 启动某服务           | service httpd start          | systemctl start httpd.service      |
| 停止某服务           | service httpd stop           | systemctl stop httpd.service       |
| 重启某服务           | service httpd restart        | systemctl restart httpd.service    |

# 5.修改系统yum源地址为阿里云

> 引用：https://blog.csdn.net/qq_28710983/article/details/79339116

```		shell
- 首先备份系统自带yum源配置文件/etc/yum.repos.d/CentOS-Base.repo
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
- 下载ailiyun的yum源配置文件到/etc/yum.repos.d/
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
- 运行yum makecache生成缓存,在本地生成索引
yum makecache
- 这时候再更新系统就会看到以下mirrors.aliyun.com信息
yum -y update
```

# 6.vim实用快捷键能

- 基本操作和复制粘贴类

```shell
- i 切换到输入模式，以输入字符。
- x 删除当前光标所在处的字符
- : 切换到底线命令模式，以在最底一行输入命令。
- nG:跳转到指定行
- H,M,L:跳转到屏幕的第一行（gg==1g），中国行，最后一行
 n+Enter:想下移动n行
 
- u:恢复。(常用)
- ctrl+r:撤销恢复
- J:将光标所在行与下一行的数据结合成同一行
- nyy:复制n行代码
- p,P:粘贴到当前行的下一行或上一行
- ndd: 删除当前行下面的n行
```

- 搜索和替换

```shell
- /word :搜索关键词word
- shift+n,n,N:搜索上一个或者下一个关键词

```

