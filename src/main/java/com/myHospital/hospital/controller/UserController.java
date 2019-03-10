package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.CommonService;
import com.myHospital.hospital.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/16/2019
 */

@Controller
@RequestMapping("/users")
public class UserController {

    private CommonService commonService;

    @Autowired
    private UsersService usersService;

    /**
     * 获取表单值进行用户注册
     * @param users
     * @return
     */
    @PostMapping("/resign")
    public ModelAndView resignUser(@ModelAttribute Users users){
        commonService.add(users,null,"user");
        return new ModelAndView("users/login");
    }


}
