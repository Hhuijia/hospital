package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.CommonService;
import com.myHospital.hospital.service.DoctorService;
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
    private DoctorService doctorService;

    @Autowired
    private MedicineDepartmentService medicineDepartmentService;

    /**
     * 快速查询
     * @param appointmentDate 预约日期
     * @param departmentId 科室Id
     * @return 预约界面
     */
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
        List<Department> departments = medicineDepartmentService.findAllDepartment();
        modelAndView.addObject("title","预约");
        modelAndView.addObject("user", user);
        modelAndView.addObject("appointment",new Appointment());
        modelAndView.setViewName("user/makeAppointment");
        return modelAndView;
    }

    @GetMapping("/ShowAppointmentPage1")
    public ModelAndView ShowAppointmentPage1(){
        log.info("********用户界面/预约*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        List<Department> departments = medicineDepartmentService.findAllDepartment();
        List<Doctors> doctors = commonService.findAll("doctor");
        modelAndView.addObject("title","预约");
        modelAndView.addObject("user", user);
        modelAndView.addObject("departments",departments);
        modelAndView.addObject("doctors",doctors);
        modelAndView.addObject("appointment",new Appointment());
        modelAndView.setViewName("user/makeAppointment");
        return modelAndView;
    }

    @PostMapping("/findDoctor")
    @ResponseBody
    public List<Doctors> findDoctor(@RequestParam String departmentName){
        log.info("********二级联动找医生*********");
        log.info("***********[{}]*******",doctorService.findDoctorInSameDepartment(departmentName));
        return doctorService.findDoctorInSameDepartment(departmentName);
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
     * @return 个人预约界面
     */
    @GetMapping("/cancelAppointment")
    public ModelAndView cancelAppointment(@RequestParam String AppointmentId){
        return new ModelAndView("redirect:checkMyAppointment");
    }


    /**
     * 查看个人信息
     * @return 个人信息页面
     */
    @GetMapping("/checkMyInfo")
    public ModelAndView checkMyInfo(){
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","查看个人信息");
        Users users = usersService.findUserByID(user.getUserId());
        modelAndView.addObject("users", users);
        modelAndView.setViewName("user/myPage");
        return modelAndView;
    }

    /**
     * 查看我的预约
     * @return 个人预约界面
     */
    @GetMapping("/checkMyAppointment")
    public ModelAndView checkMyAppointment(){
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","查看我的预约");
        List<Appointment> appointments = usersService.findAllAppointmentOfOneByUserId(user.getUserId());
        modelAndView.addObject("appointments", appointments);
        return modelAndView;
    }
}