package cn.java.family.spring.security02.config;

import cn.java.family.spring.security02.handler.MyAuthenticationFailureHandler;
import cn.java.family.spring.security02.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description Spring Security的配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //禁用跨站csrf攻击防御，后面的章节会专门讲解
                .formLogin()//表单登录
                .loginPage("/login/page")//一旦用户的请求没有权限就跳转到这个页面
                .loginProcessingUrl("/login")//登录表单form中action的地址，也就是处理认证请求的路径
                .usernameParameter("username")///登录表单form中用户名输入框input的name名，不修改的话默认是username
                .passwordParameter("password")//form中密码输入框input的name名，不修改的话默认是password
//                .defaultSuccessUrl("/")//登录认证成功后默认转跳的路径
//                .failureUrl("/login/page")  //登陆失败的跳转的路径
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login/page","/login").permitAll()//不需要通过登录验证就可以被访问的资源路径
                .antMatchers("/","/hello1") //资源路径匹配
                .hasAnyAuthority("ROLE_user","ROLE_admin")  //user角色和admin角色都可以访问
                .antMatchers("/hello2")  //资源路径匹配
                .hasAnyRole("admin")  //admin角色可以访问
                .anyRequest().authenticated();  //除了上面的配置的规则，访问其他的资源都需要登录认证通过才可以访问
    }

    /**
     * 用户、角色配置
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()//内存的方式配置，这里还支持从数据库中加载
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("user")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("admin")
                .and()
                .passwordEncoder(passwordEncoder());//配置BCrypt加密
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
