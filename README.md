### cas server 配置




#### 加载自己的配置文件

	修改propertyFileConfigurer.xml 文件来增加自己的配置文件 
#### 配置数据库 dataSource
	修改deployerConfigContext.xml这个文件配置自己的数据源

#### 登录验证 
	根据自己的业务修改 QueryDatabaseAuthenticationHandler
	
#### 自定义登录页面
	修改casLoginView.jsp页面 尽量保持原来的jsp标签引入不变，然后根据自己的设计增加登录相关代码

#### 自定义登出业务
	用户退出登录，是需要传service值(这个是登出后的跳转地址)。
	例如登出的请求为http://localhost:8082/cas-server-webapp/login?service=http://www.baidu.com
	
	还有一个问题：cas4.0默认对http请求方式不是安全registeredServicesList里面的，
	所以需要修改配置文件deployerConfigContext.xml 在registeredServicesList中增加http的请求。

#### 其他内容    


	