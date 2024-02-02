package com.jasper.spring_security.config;


import com.jasper.spring_security.filter.JWTAuthorizationFilter;
import com.jasper.spring_security.handler.CustomLogoutSuccessHandler;
import com.jasper.spring_security.service.SysPersistLoginService;
import com.jasper.spring_security.service.impl.SysUserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {
//    userDetailService
    private final SysUserDetailServiceImpl sysUserDetailServiceImpl;
//    token filter
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
//    dynamic authority
    private final RequestAuthorizationManager requestAuthorizationManager;
//    remember me
    private final SysPersistLoginService sysPersistLoginServiceImpl;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 配置认证管理器
     * @return AuthenticationManager  DaoAuthenticationProvider将userDetailService passwordEncoder 整合起来
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(sysUserDetailServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        将自己写的filter放到UsernamePasswordAuthenticationFilter(验证username and password）之前
        httpSecurity.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(
                auth -> auth.requestMatchers("/auth/login","/oauth/notify","oauth/to_authorize","to_login")
                        .permitAll()
                        .anyRequest()
                        .access(requestAuthorizationManager)

        );
//        remember me
        httpSecurity.rememberMe(
                remember->
                        remember.rememberMeCookieName("rememberMe")
                                .rememberMeParameter("remember-me").tokenRepository((PersistentTokenRepository)sysPersistLoginServiceImpl)
                                .tokenValiditySeconds(60*60) // 1 hour
        );
//                httpSecurity.formLogin(
//                form -> form.loginPage("/to_login")
//                        .loginProcessingUrl("/toLogin") //处理前端的请求
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/index")
//        );
        httpSecurity.logout(logout->logout.logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .logoutSuccessUrl("/to_login").deleteCookies("rememberMe"));

        return httpSecurity.build();
    }




//    @Resource
//    private DataSource dataSource;
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository(){
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        // 设置为true要保障数据库该表不存在，不然会报异常哦
//        // 所以第二次打开服务器应用程序的时候得把它设为false
//        tokenRepository.setCreateTableOnStartup(true);
//        return tokenRepository;
//    }
//














    //    @Bean
//    public UserDetailsService userDetailsService(){
////        123456 test
//        UserDetails admin = User.withUsername("admin").password("$2a$10$8mHWG2c7ZeLwtUcSClsZAeVQQPxfqe6sk2GfS/rv5ZfGZw2c56/U6")
//                .roles("admin", "user").
//                build();
//        UserDetails vip = User.withUsername("vip").password("$2a$10$8mHWG2c7ZeLwtUcSClsZAeVQQPxfqe6sk2GfS/rv5ZfGZw2c56/U6").
//                roles("user")
//                .authorities("test:show")
//                .build();
//        return new InMemoryUserDetailsManager(admin, vip);
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(Customizer.withDefaults());
//        httpSecurity.authorizeHttpRequests(
//                auth -> auth.
//                        requestMatchers("/test").hasAnyRole("admin","user").
//                        requestMatchers("/to_login").permitAll().
//                        anyRequest().authenticated()
//        );
//        httpSecurity.formLogin(
//                form -> form.loginPage("/to_login")
//                        .loginProcessingUrl("/toLogin") //处理前端的请求
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/index")
//        );
//
//        return httpSecurity.build();
//
//    }

}
