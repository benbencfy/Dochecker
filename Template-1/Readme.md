todo 晚点Typora写好再复制上来

工具开发模板 SpringIOC容器版本
主要是为了方便工具开发
注意action并没有受Spring管理

可以使用切面技术

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>4.3.7.RELEASE</version>
</dependency>
```

### 修改项目名称

1. 第一步：修改项目名称

   - 修改 ToolTemplate-Java  ——–>  自定义项目名

2. 第二步：修改包名

3. 第三步：修改`com.wetoband.template.action`包下的类名

   - ToolTemplateAction ——> 自定义类名

   - 同时修改`src`下的`config.xml`的配置：

     ```diff
     <?xml version="1.0" encoding="UTF-8"?>
     <Config>
     	<!-- 开发包版本，重要参数，如不了解作用，切勿修改 -->
     	<SDKVersion>3.0</SDKVersion>
     	<!-- 需要加载器加载的类(入口类)的全名 -->
     +	<EntryClass>action包下的类的全路径</EntryClass>
     	<!-- 工具配置参数 -->
     	<toolConfig>{aid:"21419389378723787447"}</toolConfig>
     	<!-- 普通声明式调用配置 -->
     	<UrlAlias name="abc">http://127.0.0.1:8080</UrlAlias>
     </Config>
     ```

4. 修改`pom.xml`的名称

### 导入项目依赖的jar包

1. 导入项目路径下的`lib`
2. 导入项目路径下的`WebRoot/WEB-INF/lib`

### 设置WebRoot为项目的web

1. 进入idea的Project Structure，找到Modules下的项目并展开
2. 点击上面的加号，在菜单中选中`web`
3. 修改弹出的右侧设置
   1. 修改`Deployment Descriptors`的Path为WebRoot下的web.xml
   2. 修改`Web Resource Directories`下的`Web Resource Director`的路径为WebRoot路径

### 添加Tomcat服务器

点击idea界面右上角的`Add Configuration`，进去添加一个Tomcat服务器就可以运行了。

### 修改AppConfig.java的ComponentScan注解

```java
@ComponentScan("Java项目的包路径")
```

如模板中的就是：`com.wetoband.template`

### 注意事项

1. 项目路径不可为中文路径；
2. 前端路由最好使用Hash；
