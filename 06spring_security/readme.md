## 登录
默认有一个拦截器 ，拦截判断是否登录，有默认的登录界面

## authorization 认证
- pojo implements UserDetails
- UserDetailsService impl
- 配置类关联AuthenticationManager 与自定义的userDetailService

## 权限
- 告知security 用户有什么权限 
- 	Collection<? extends GrantedAuthority> getAuthorities();
-  后续操作根据token获取用户

### 俩种
- 基于请求
- 基于方法


## dynamic authority
- .anyRequest().access()
- AuthorizationManager interface impl
- 查询访问该接口需要的权限 然后根据当前认证用户拥有的权限，判断是否包含接口所需权限

### disadvantage
- 每次请求都要查询数据库
- 优化：查询所有权限，存入内存，每次请求直接从内存中获取
- 优化：查询所有权限， 存入redis，每次请求直接从redis中获取

## 记住我 持久化login
- 通过cookie实现
- 使用hash保持基于cookie令牌的安全性
- 使用数据库存储生成的令牌redis
### disadvantage

- cookie 容易被任何用户代理使用