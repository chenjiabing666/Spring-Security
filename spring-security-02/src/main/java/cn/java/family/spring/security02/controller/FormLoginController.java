package cn.java.family.spring.security02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description 用于页面跳转
 */
@Controller
public class FormLoginController {

    /**
     * 登录页面
     */
    @GetMapping("/login/page")
    public String login(){
        return "login";
    }
}
