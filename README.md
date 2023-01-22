# 校园猫管理系统
* ### 功能
    * #### 游客
      >* 用户注册、登录
      >* 查看全部猫咪信息
      >* 查看猫咪投喂、出现记录，以及具体猫咪记录
    * #### 普通用户
      >* 全部游客功能
      >* 用户注销
      >* 查看本人提交记录
      >* 申请添加猫咪信息
      >* 申请添加猫咪投喂、出现记录
      >* 删除、申请修改本人提交记录
    * #### 管理员
      >* 全部普通用户功能
      >* 操作无需申请，可直接成功
      >* 修改、删除所有猫咪信息和记录
      >* 审核用户添加、修改操作
* ### 工具选择一览
    * #### 前端
      >* vue2：框架，简化dom操作
      >* axios：发送异步http请求
      >* bootstrap：ui库
    * #### 后端
      >* springboot2.7：服务端框架，内置tomcat
      >* mybatis：持久层框架
      >* mysql8：数据库
* ### 启动
  > 使用IntelliJ IDEA打开项目；运行catFeeding.sql脚本将 表导入数据库；
  > 修改/src/main/resources/application.yml中
  > driver-class-name(数据库驱动类名，本人采用druid，可不修改)、
  > url(数据库url)、username(用户名)、password(密码)
  > 为自己的数据库信息；然后运行 CampusCatsApplication
  > 启动服务器
  > 浏览器打开 http://localhost:8080/CampusCats/
  > 进入主页面。