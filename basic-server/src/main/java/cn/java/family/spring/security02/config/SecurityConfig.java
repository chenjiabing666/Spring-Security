package cn.java.family.spring.security02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description Spring Security的配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()//开启httpbasic认证
                .and()
                .authorizeRequests()
//                .antMatchers("/oauth/login").permitAll()
                .anyRequest()
                .authenticated();//所有请求都需要登录认证才能访问
    }
}
