package cn.java.family.spring.security02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 公众号：码猿技术专栏
 * @url: www.java-family.cn
 * @description
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/test")
    public String test(){
        return "success";
    }
}
