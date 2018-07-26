#Minlia Rocket项目功能规划  

----

#Minlia Rocket
##以敏捷、便利、优雅的姿态助力企业级开发。


### Conceptions:  
 __待君哥序：__ 

过度依赖个人  
无法快速交付  
各种BUG产生  
代码结构混乱  


### ContextHolder
 __目的：__ 
为项目提供便利的容器获取途径
 __现状：__ 
在项目里面经常会需要获取Spring容器，即（ApplicationContext），实现流程大致如下，实现一个自定义的ApplicationContextAware接口，再将此类注册为一个Spring所需的Bean。如果每个项目里面都有这种类的存在我们认为就做了些重复工作，能不能一次定义处处可用呢？我们考虑将这种类型的工作独立出来，为各项目提供支持。

 __解决方案：__ 
在启动时自动注册ContextHolder到Spring容器。即可在项目任意地方静态调用此类进行容器获取。

 __使用方式：__ 
无需进行额外配置。

```
public class ContextHolder implements ApplicationContextAware {

  private static ApplicationContext applicatioContext;

  public static ApplicationContext getContext() {
    return ContextHolder.applicatioContext;
  }

  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    ContextHolder.applicatioContext = ctx;
  }
}
```


#### Stateful response body  
 __目的：__ 
为项目定义状态化的返回体
 __现状：__ 
返回体没有标准，各项目不同的返回定义可能会带来无法顺畅对接的后果。在前端工业化盛行的时代后端的一个返回结构变更导致前端代码需要不断修改，无法使用拦截器等方式进行统一处理，所以急需要一套标准来定义。

__解决方案：__ 
提出了返回状态化结果的概念，返回体都携带`code`,`message`等定义。

 __使用方式：__ 

 __示例：__ 
```
{
  "payload": {
    "result": 43
  },
  "timestamp": 1532578678960,
  "requestId": "34760453573840896",
  "code": 1,
  "status": 1,
  "message": "OK",
  "success": true
}
```


### Loggable
 __目的：__ 为项目提供打印请求和返回参数的功能
 __现状：__ 
__解决方案：__ 
 __使用方式：__ 


### Pretend

### Problem or Exceptions

### Api documentation with swagger

#### Abstraction Service

#### Abstraction Endpoint

#### Data Jpa

#### Data batis 

#### Security

#### Internationalization  

 __目的：__ 
通用的多语言支持解决方案
重点：通用

当前spring mvc 为我们提供了国际化解决方案， 我们在使用的时候需要自行开发一些功能以满足业务系统需求。如：语言切换的方式，译文存储方式等。

代码国际化：指在项目运行期不会改变的内容
类似对类（class）的国际化
class有类名，属性、方法等等代码层面的内容，在抛出时异常被国际化，
数据国际化：如商品的不同语言版本
类似对具体对象示例（instance）的国际化
每个实例有这些实例是在运行期产生的（），己的有

```
{
"productName":"Apple Book Pro",
}

{
"productName":"苹果电脑",
}

```


功能特征：

##### 可以选择语言参数传递的位置  
Request parameter 
Header

##### 可自定义参数名称

Configuration in `application.properties`
系统优先从query parameter里取值，如果取不到则从header里获取


```
system.i18n.language-request-parameter-name=lang
system.i18n.language-request-header-parameter-name=X-Request-Lang
```



##### 国际化译文持久化  

###### 标准文件存储方式

```
system.i18n.basename=i18n/messages


api.code.4011=认证失败，密码不正确
api.code.4012=认证失败，账户已过期
api.code.4013=认证失败，账户已锁定
api.code.4014=认证失败，账户已禁用
api.code.4015=认证失败，凭证已过期

```


###### 数据库存储国际化译文的方式  
国际化译文的更新，调用刷新国际化译文接口进行刷新

```
1	api.code.4011	zh_CN	认证失败，密码不正确					
2	api.code.4011	en_US	Authentication failed, incorrect credentials.	
```

##### 数据国际化
由用户自行添加的数据

#### 状态化返回结果  
在项目里面定义了标准的状态化的返回结果  

登录失败示例  

```
{
    "type": "https://c.minlia.com/4011",
    "title": "Expectation Failed",
    "status": 417,
    "code": 4011,
    "message": "认证失败，密码不正确"
} 

{
    "type": "https://c.minlia.com/4011",
    "title": "Expectation Failed",
    "status": 417,
    "code": 4011,
    "message": "Authentication failed, incorrect credentials."
}

```

登录成功示例  


```
{
    "payload": {
        "accessToken": {
            "value": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImp0aSI6IjcxZmE0NjU3LTRjYjYtNGNiMC04MjZjLWFmZTFjZDM1NzdlMCIsInNjb3BlcyI6ImEsYixjIiwiaXNzIjoibWlubGlhLmNvbSIsImlhdCI6MTUzMjU3NjIwNSwiZXhwIjoxNTM1MTY4MjA1fQ.Uf42CuILKKc5tN_axk_XCuGWvx0r73sXKoGv9um-gnbG-YYnDpMGfIFMwUNZVemmWsA_Ej_gwrrqcnKUHWiliQ",
            "expiration": 1535168205359,
            "issuedAt": 1532576205359
        },
        "refreshToken": {
            "value": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImp0aSI6IjNhMjNjY2EzLWFkOTYtNGEzNy04MTU2LTcyMzRmYTYxNDAyNCIsInNjb3BlcyI6WyJSRUZSRVNIX1RPS0VOIl0sImlzcyI6Im1pbmxpYS5jb20iLCJpYXQiOjE1MzI1NzYyMDUsImV4cCI6MTUzMjU3ODAwNX0.5JnQxaDqya1EUvASwZDGco_8UyX3hPrrFwU7x5yO0a4UXEFc9Nm8GuHIcXrhJ_FOI_NdW1Mllt3GddIqEUFbYA"
        }
    },
    "timestamp": 1532576205513,
    "requestId": "34750079185195008",
    "code": 1,
    "status": 1,
    "message": "OK",
    "success": true
}
```


```

http://localhost:7001/api/v1/queen/count


accept: application/json;charset=UTF-8
origin: http://localhost:8080
Content-Type: application/json
X-Auth-Token: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImp0aSI6ImQ0MGNlZDAwLTk0ZjktNGE4ZC04MjlhLTJjM2FkNWJmNThlOSIsInNjb3BlcyI6ImEsYixjIiwiaXNzIjoibWlubGlhLmNvbSIsImlhdCI6MTUzMjYyMzE5NiwiZXhwIjoxNTM1MjE1MTk2fQ.wF4OLvzm1Pjzt3zbVxrCL_uwhDScp1ULfz3kSY7UbDLa__phoYGDHrs5-W8wZVi1gj09Y8HI5lGNQ1Zk6bPg2A


{ "name": "string"}
```