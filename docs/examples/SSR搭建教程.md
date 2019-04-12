`前置条件`: 购买一台VPS服务器

### 1.获取安装文件

```shell
wget --no-check-certificate https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocksR.sh
```

### 2.添加可执行权限，并执行安装

```shell
chmod +x shadowsocksR.sh
sh /usr/local/shadowsocksR/shadowsocksR.sh
```

### 3.安装参数配置说明（278）

```shell
1、新增：安装时可选 13 种加密方式的其中之一（none 是不加密）。如下所示
none
aes-256-cfb
aes-192-cfb
aes-128-cfb
aes-256-cfb8
aes-192-cfb8
aes-128-cfb8
aes-256-ctr
aes-192-ctr
aes-128-ctr
chacha20-ietf
chacha20
rc4-md5
rc4-md5-6
2、新增：安装时可选 7 种协议（protocol）的其中之一。如下所示：
origin
verify_deflate
auth_sha1_v4
auth_sha1_v4_compatible
auth_aes128_md5
auth_aes128_sha1
auth_chain_a
auth_chain_b
3、新增：安装时可选 9 种混淆（obfs）的其中之一。如下所示：
plain
http_simple
http_simple_compatible
http_post
http_post_compatible
tls1.2_ticket_auth
tls1.2_ticket_auth_compatible
tls1.2_ticket_fastauth
tls1.2_ticket_fastauth_compatible
```

### 4.安装结果

```shell
Congratulations, ShadowsocksR server install completed!
Your Server IP        :  128.199.187.48 
Your Server Port      :  8825 
Your Password         :  kiwipeach 
Your Protocol         :  auth_chain_a 
Your obfs             :  tls1.2_ticket_auth 
Your Encryption Method:  aes-256-cfb 

Welcome to visit:https://shadowsocks.be/9.html
Enjoy it!
```

### 5.SSR相关命令以及配置

```shell
- 命令查看
启动：/etc/init.d/shadowsocks start
停止：/etc/init.d/shadowsocks stop
重启：/etc/init.d/shadowsocks restart
状态：/etc/init.d/shadowsocks status
卸载: /usr/local/shadowsocksR/shadowsocksR.sh uninstall

- 相关配置
配置文件路径：/etc/shadowsocks.json
日志文件路径：/var/log/shadowsocks.log
代码安装目录：/usr/local/shadowsocks
```

### 6.下载SSRWindows客户端进行连接配置

> 下载地址：https://github.com/shadowsocks/shadowsocks-windows/releases

![1540815533130](assets/1540815533130.png)