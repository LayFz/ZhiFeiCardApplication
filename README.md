# 智菲科技后端基础服务样例

## 一、项目目录结构
```
scaffold_service
 |-- db -数据库脚本资料文件夹
 |-- src
  |-- main
   |-- java
    |-- com.zfkj.demo
     |-- common -公共类文件夹
      |-- annotation -自定义注解文件夹
      |-- config -代码配置文件夹
      |-- constant -常量类文件夹
      |-- enums -枚举类文件夹
      |-- exception -自定义异常文件夹
      |-- utils -工具类文件夹
     |-- controller -控制层类文件夹
     |-- service -业务逻辑服务层文件夹
     |-- dao -数据持久层文件夹
      |-- entity -数据库实体对象映射文件夹
      |-- mapper -数据库mapper文件夹
      |-- repository -数据库repository文件夹
     |-- vo -数据传输对象文件夹
   |-- resources -资源配置文件夹
  |-- test
 |-- .gitignore
 |-- mvnw
 |-- mvnw.cmd
 |-- pom.xml
 |-- README.md
 |-- scaffold_service.iml
```

---

## 二、项目集成组件
### 2.1、swagger
* 版本：3.0
* 访问地址：http://ip:port/swagger-ui/

### 2.2、mysql 
* 版本：8.0.11

### 2.3、springboot
* 版本：2.6.1

### 2.4、mybatis-plus
* 版本：3.1.0

---

## 三、注意事项
### 3.1、接口相关
* 接口入参对象参数校验时，入参对象前面必须加上 @Valid 注解，参数校验相关函数及用法参考
  https://blog.csdn.net/whk_15502266662/article/details/107981940
  



