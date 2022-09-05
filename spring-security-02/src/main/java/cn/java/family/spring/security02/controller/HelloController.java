package cn.java.family.spring.security02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description hello测试
 */
@RestController
public class HelloController {

    @RequestMapping("/hello1")
    public String hello1(){
        return "hello1";
    }

    @RequestMapping("/hello2")
    public String hello2(){
        return "hello2";
    }
}
