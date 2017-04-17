# yunsearch
网盘搜索主页：[http://60.205.218.214](http://60.205.218.214)

yunsearch是一个百度网盘资源的搜索引擎，利用yunsearch可以帮我们方便地搜索到需要的资源。

yunsearch是我另外一个项目agama爬虫框架的实践产物，yunsearch演示了agama如何结合Spring Boot的使用，并以Elasticsearch作为搜索引擎为网盘资源提供搜索。

# 使用
1.导入yunsearch的数据库表，网盘资源的爬取需要有一个起点。

2.启动Elasticsearch，项目版本5.0以上。

3.由于项目依赖的agama并没有发布到maven仓库，需要自行导到本地maven。
- 克隆``git clone https://github.com/GinPonson/agama.git``
- ``mvn install``安装项目依赖

4.``mvn install``安装项目依赖，``mvn package``打成war包，放到servlet容器上运行。

# TODO
- [X] 去除多次分享导致的文件重复
- [ ] 文件的分类

# 项目依赖
Spring Boot、Elasticsearch、MySql、Agama
ss

