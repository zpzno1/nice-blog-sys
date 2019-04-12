#### 易联众银山大厦双网卡配置脚本

```powershell
@echo off
@echo ***************
@echo **** 银山大厦双网卡配置 *****
@echo ***** Create by Kiwipeach ******
@echo ***** route print ****
@echo ***************
@echo ===========================================
@echo #正在配置内网网卡...
netsh interface ip set address name=”本地连接” source=static addr=192.168.210.21 mask=255.255.255.0 gateway=192.168.210.254

@echo #正在配置永久路由...
route -p add 19.15.232.0 mask 255.255.255.0 192.168.210.254
route -p add 192.168.1.0 mask 255.255.255.0 192.168.1.1

@echo #配置永久路由成功...

echo.
@echo #完成双网卡配置
@echo =========================================
echo.
pause
```

