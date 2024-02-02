package com.jasper.spring_security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class OAuth2LoginConfig {
    private final DataSource dataSource;
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.giteeClientRegistration());
    }
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        // 使用 JdbcClientRegistrationRepository 代替 InMemoryClientRegistrationRepository
//        return new JdbcClientRegistrationRepository(this.dataSource);
//    }

    private ClientRegistration giteeClientRegistration() {
        return ClientRegistration.withRegistrationId("gitee")
                .clientId("feb31e7e0e84faf41a038ca503a44c744fac0b169e48e5a5defb680eab58ac64")
                .clientSecret("ee89f74e0f51f079095b9483b4e536be0f726a5709e3d67ca8cca7e367557dc8")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/oauth/notify")
                .scope("user_info",  "emails")
                .authorizationUri("https://gitee.com/oauth/authorize")
                .tokenUri("https://gitee.com/oauth/token")
                .userInfoUri(" https://gitee.com/api/v5/user")
                .userNameAttributeName("name")
                .clientName("gitee")
                .build();
    }
}