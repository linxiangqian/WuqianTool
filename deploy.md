## 部署到仓库指令 - 控制台执行

mvn clean deploy -P release

https://central.sonatype.com/publishing

## gpg: signing failed: Inappropriate ioctl for device / [INFO] Signer 'gpg' is signing 4 files
### gpg 需要输入密码，但当前环境没有 TTY
export GPG_TTY=$(tty)