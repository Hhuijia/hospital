package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.CommonService;
import com.myHospital.hospital.service.MedicineDepartmentService;
import com.myHospital.hospital.service.UsersService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/16/2019
 */

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MedicineDepartmentService medicineDepartmentService;

    /**
     * 获取表单值进行用户注册
     * @param users 用户
     * @return 登录界面
     */
    @PostMapping("/resign")
    public ModelAndView resignUser(@ModelAttribute Users users, @RequestParam List<String> roleIds){
        commonService.add(users,null,roleIds,"user");
        return new ModelAndView("users/login");
    }

    @GetMapping("/ShowAppointmentPage")
    public ModelAndView ShowAppointmentPage(@RequestParam String appointmentDate, @RequestParam String departmentId){
        log.info("********用户界面/预约*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        if (appointmentDate != null && !appointmentDate.isEmpty()){
            log.info("********预约时间需要看是否添加一个排班表*********");
        } else if (departmentId != null && !departmentId.isEmpty()){
            Department department = medicineDepartmentService.findDepartmentById(departmentId);
            modelAndView.addObject("department",department);
        }
        modelAndView.addObject("title","预约");
        modelAndView.addObject("username",user.getUserName());
        Users users = usersService.findUserByID(user.getUserId());
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users/makeAppointment");
        return modelAndView;
    }

    /**
     * 添加预约
     * @param appointment 预约信息
     * @return 预约成功界面
     */
    @PostMapping("/makeAppointment")
    public ModelAndView makeAppointment(@ModelAttribute Appointment appointment){
        log.info("********用户界面/预约*********");
        usersService.makeAppointment(appointment);
        return new ModelAndView("redirect:success");
    }

    /**
     * 取消预约
     * @param AppointmentId 预约Id
     * @return
     */
    @GetMapping("/cancelAppointment")
    public ModelAndView cancelAppointment(@RequestParam String AppointmentId){
        return new ModelAndView("redirect:checkMyAppointment");
    }

    /**
     * 显示科室详情
     * @param departmentId
     * @return
     */
    @GetMapping("/showDepartmentDetail")
    public  ModelAndView showDepartmentDetail(@RequestParam String departmentId){
        log.info("********用户界面/科室详情*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        Department department = medicineDepartmentService.findDepartmentById(departmentId);
        modelAndView.addObject("title","科室详情");
        modelAndView.addObject("username",user.getUserName());
        modelAndView.addObject("department",department);
        return modelAndView;
    }

    /**
     * 查看个人信息
     * @return
     */
    @GetMapping("/checkMyInfo")
    public ModelAndView checkMyInfo(){
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","查看个人信息");
        modelAndView.addObject("username",user.getUserName());
        Users users = usersService.findUserByID(user.getUserId());
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users/myPage");
        return modelAndView;
    }

    /**
     * 查看我的预约
     * @return
     */
    @GetMapping("/checkMyAppointment")
    public ModelAndView checkMyAppointment(){
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","查看我的预约");
        modelAndView.addObject("username",user.getUserName());
        List<Appointment> appointments = usersService.findAllAppointmentOfOneByUserId(user.getUserId());
        modelAndView.addObject("appointments", appointments);
        return modelAndView;
    }


}
