package com.myHospital.hospital.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @GetMapping(value = "/index")
    public ModelAndView index() {
        log.info("********登录成功*********");
        ModelAndView index = new ModelAndView();
        String title = " ";
       index.setViewName("common/index");
        index.addObject("title",title);
        return index;
    }

    @GetMapping("/notLogin")
    public ModelAndView notLogin(){
        //未登录，跳转到登录界面
        log.info("********未登录，跳转到登录界面*********");
        return new ModelAndView("common/login");
    }

    @GetMapping("/notRole")
    public ModelAndView notRole(){
        //无权限，跳转到首页
        log.info("********无权限，跳转到首页*********");
        return new ModelAndView("common/error500");
    }

    @GetMapping("/loginOut")
    public ModelAndView loginOut(){
        //用户注销，跳转到登录界面
        log.info("********用户注销，跳转到登录界面*********");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("common/login");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String userIDNum, @RequestParam String userPwd){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userIDNum, userPwd);
        ModelAndView modelAndView = new ModelAndView();
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException ice){
            log.info("********登录失败*********");
            log.info("对用户[{}]进行登录验证,验证未通过,密码错误",userIDNum);
            modelAndView.setViewName("common/login");
            modelAndView.addObject("errorMsg","对用户"+userIDNum+"进行登录验证,验证未通过,密码错误");
            token.clear();
        }catch (UnknownAccountException uae){
            log.info("********登录失败*********");
            log.info("对用户[{}]进行登录验证,验证未通过,用户不存在",userIDNum);
            modelAndView.setViewName("common/login");
            modelAndView.addObject("errorMsg","对用户"+userIDNum+"进行登录验证,验证未通过,用户不存在");
            token.clear();
        }
        log.info("********登录成功*********");
        modelAndView.setViewName("common/index");
        modelAndView.addObject("title","医院后台管理系统");
        modelAndView.addObject("username",userIDNum.substring(0,4));
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginFirst(){
        log.info("********跳转到登录界面*********");
        ModelAndView view = new ModelAndView();
        view.setViewName("common/login");
        view.addObject("title","login");
        return view;
    }
}
