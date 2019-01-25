package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.UsersService;
import com.myHospital.hospital.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    public UsersService usersService;

    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping("/notLogin")
    public ModelAndView notLogin(){
        //未登录，跳转到登录界面
        return new ModelAndView("login");
    }

    @GetMapping("/notRole")
    public ModelAndView notRole(){
        //无权限，跳转到首页
        return new ModelAndView("notRole");
    }

    @GetMapping("/loginOut")
    public ModelAndView loginOut(){
        //用户注销，跳转到登录界面
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("login");
    }
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String userIDNum, @RequestParam String userPwd){
        UsernamePasswordToken token = new UsernamePasswordToken(userIDNum, userPwd);
        Subject subject = SecurityUtils.getSubject();
        ModelAndView modelAndView = new ModelAndView();
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException ice){
            System.out.println("password error!");
            modelAndView.setViewName("login");
            return modelAndView;
        }catch (UnknownAccountException uae){
            System.out.println("userPhone error!");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        String role = usersService.findRoleByIDNum(userIDNum);
        if ("user".equals(role)){
            System.out.println("user!");
            modelAndView.setViewName("user/index");
            return modelAndView;
        }else if ("admin".equals(role)){
            System.out.println("admin!");
            modelAndView.setViewName("admin/index");
            return modelAndView;
        }else if ("doctor".equals(role)){
            System.out.println("doctor!");
            modelAndView.setViewName("doctor/index");
            return modelAndView;
        }

        Users users = usersService.findUserByIDNum(userIDNum);
        subject.getSession().setAttribute("user", users);
        return modelAndView;
    }

}
