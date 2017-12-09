用android android studio导入时路径要选择android_big-nerd-ranch\ch12DateDialog\下的CriminalIntent,不能选android_big-nerd-ranch\ch12DateDialog

android studio导入后的配置  
1 导入时按提示更新android-sdk路径  
2 file-setting-build,execution,deployment中的选择use locale gradle distribution, 下载gradle-2.2-bin, 解压后制定这个路径  
3 然后分别下载sdk 21, build tools 20  
4 File-->Project-->Structrue-->SDK Location-->JDK location Use embedded JDK 前面勾去掉，指定自己的JDK地址。

