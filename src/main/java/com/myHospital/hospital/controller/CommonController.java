package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.UsersService;
import com.myHospital.hospital.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping(value = "/index")
    public ModelAndView index() {
        log.info("********登陆成功*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView index = new ModelAndView();
        index.setViewName("show");
        return index;
    }

    @GetMapping("/notLogin")
    public ModelAndView notLogin(){
        //未登录，跳转到登录界面
        log.info("********未登录，跳转到登录界面*********");
        return new ModelAndView("login");
    }

    @GetMapping("/notRole")
    public ModelAndView notRole(){
        //无权限，跳转到首页
        log.info("********无权限，跳转到首页*********");
        return new ModelAndView("notRole");
    }

    @GetMapping("/loginOut")
    public ModelAndView loginOut(){
        //用户注销，跳转到登录界面
        log.info("********用户注销，跳转到登录界面*********");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("login");
    }
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String userIDNum, @RequestParam String userPwd){
        log.info("********登录失败*********");
        UsernamePasswordToken token = new UsernamePasswordToken(userIDNum, userPwd);
        Subject subject = SecurityUtils.getSubject();
        ModelAndView modelAndView = new ModelAndView();
        String errorMsg = " ";
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException ice){
            log.info("对用户[{}]进行登录验证,验证未通过,密码错误",userIDNum);
            errorMsg = "对用户"+userIDNum+"进行登录验证,验证未通过,密码错误";
        }catch (UnknownAccountException uae){
            log.info("对用户[{}]进行登录验证,验证未通过,用户不存在",userIDNum);
            errorMsg = "对用户"+userIDNum+"进行登录验证,验证未通过,用户不存在";
        }finally {
            modelAndView.setViewName("login");
            modelAndView.addObject("errorMsg",errorMsg);
            token.clear();
            return modelAndView;
        }
//        String role = usersService.findRoleByIDNum(userIDNum);
//        if ("user".equals(role)){
//            System.out.println("user!");
//            modelAndView.setViewName("user/index");
//            return modelAndView;
//        }else if ("admin".equals(role)){
//            System.out.println("admin!");
//            modelAndView.setViewName("admin/index");
//            return modelAndView;
//        }else if ("doctor".equals(role)){
//            System.out.println("doctor!");
//            modelAndView.setViewName("doctor/index");
//            return modelAndView;
//        }
//
//        Users users = usersService.findUserByIDNum(userIDNum);
//        subject.getSession().setAttribute("user", users);
//        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginFirst(){
        log.info("********跳转到登录界面*********");
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        view.addObject("title","LoginUser");
        view.addObject("desc","欢迎您，请登录！");
        return view;
    }
}
