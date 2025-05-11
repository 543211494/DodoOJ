# DodoOJ

## 1.docker命令需要root权限解决方法

```shell
# 检查docker用户组是否存在
sudo grep docker /etc/group
# 将当前用户追加进docker用户组
sudo usermod -aG docker $USER
```

