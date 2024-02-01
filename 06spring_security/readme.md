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