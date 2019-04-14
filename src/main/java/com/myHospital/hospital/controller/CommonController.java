package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.MedicineDepartmentService;
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

import java.util.ArrayList;
import java.util.List;

import static com.myHospital.hospital.util.GetFeatureDayUtil.getFeatureDate;

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
    private MedicineDepartmentService medicineDepartmentService;

    @GetMapping("/index")
    public ModelAndView index() {
        log.info("********游客首页*********");
        ModelAndView modelAndView = new ModelAndView();
        List<Department> departments = medicineDepartmentService.findAllDepartment();
        modelAndView.addObject("departments",departments);
        ArrayList<String> featureDaysList = new ArrayList<>();
        for (int i = 0; i <7; i++) {
            featureDaysList.add(getFeatureDate(i));
        }
        modelAndView.addObject("featureDaysList",featureDaysList);
        modelAndView.addObject("title","珠海某某某医院");
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/otherIndex")
    public ModelAndView otherIndex() {
        log.info("********后台首页*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","医院后台管理系统");
        modelAndView.setViewName("common/index");
        return modelAndView;
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
        return new ModelAndView("500");
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
            log.info("********登录成功*********");
            List<Role> roles = usersService.findRoleByIDNum(userIDNum);
            boolean isUser = false;
            for (Role role : roles){
                if (role.getRoleName().contains("user")){
                    isUser = true;
                    break;
                }
            }
            if (isUser){
                return new ModelAndView("redirect:index");
            }else {
                if (userPwd.equals("huanghuijia")){
                    modelAndView.setViewName("common/resetPassword");
                }else {
                    return new ModelAndView("redirect:otherIndex");
                }
            }
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
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginFirst(){
        log.info("********跳转到登录界面*********");
        ModelAndView view = new ModelAndView();
        view.setViewName("common/login");
        view.addObject("title","登录");
        return view;
    }

    @PostMapping(value = "/resetPassword")
    public ModelAndView resetPassword(@RequestParam String userPwd){
        log.info("********修改密码界面*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        user.setUserPwd(userPwd);
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        int result = usersService.updatePwdByIdNum(user.getUserPwd(),user.getSalt(),user.getUserIDNum());
        log.info(String.valueOf(result));
        String resultText ="修改成功";
        if (result==0){
            resultText = "修改失败！";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/login");
        modelAndView.addObject("errorMsg",resultText);
        return modelAndView;
    }
}
