package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.DepartmentService;
import com.myHospital.hospital.service.DoctorService;
import com.myHospital.hospital.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/doctorManage")
    public ModelAndView doctorManage(){
        log.info("********管理员界面/医生管理*********");
        List<Doctors> doctors = new ArrayList<Doctors>();
        List<String> departmentName = new ArrayList<String>();
        doctors = doctorService.findAllDoctor();
        departmentName = departmentService.findAlldepartmentName();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","医生管理");
//        modelAndView.addObject("username",userIDNum.substring(0,4));
        if (doctors != null && !doctors.isEmpty()){
            modelAndView.addObject("doctors",doctors);
            log.info("[{}]",doctors);
        }
        if (departmentName != null && !departmentName.isEmpty()){
            modelAndView.addObject("departmentName",departmentName);
            log.info("[{}]",departmentName);
        }
        modelAndView.addObject("users",new Users());
        modelAndView.setViewName("admin/doctors");
        return modelAndView;
    }
    @PostMapping("/addDoctor")
    public ModelAndView addDoctor(@ModelAttribute Users users, @RequestParam String departmentName,
                                  @RequestParam String doctorTitle, @RequestParam String doctorProfession,
                                  @RequestParam int doctorMedicalServiceLife, @RequestParam String doctorIntroduction){
        log.info("********添加医生*********");
        Doctors doctors = new Doctors();
        doctors.setDoctorName(users.getUserName());
        doctors.setDoctorTitle(doctorTitle);
        doctors.setDoctorProfession(doctorProfession);
        doctors.setDoctorMedicalServiceLife(doctorMedicalServiceLife);
        doctors.setDoctorIntroduction(doctorIntroduction);
        doctorService.addDoctor(users,doctors,users.getUserIDNum(),departmentName);
        return new ModelAndView("redirect:doctorManage");
    }
    @GetMapping("/delDoctor")
    public ModelAndView delDoctor(@RequestParam String doctorId){
        log.info("********删除医生*********");
        doctorService.deleteDoctor(doctorId);
        return new ModelAndView("redirect:doctorManage");
    }
    @GetMapping("/editDoctor")
    public ModelAndView editDoctor(@RequestParam String doctorId){
        log.info("********删除医生*********");
        doctorService.deleteDoctor(doctorId);
        return new ModelAndView("redirect:doctorManage");
    }
    @GetMapping("/adminManage")
    public ModelAndView adminManage(){
        log.info("********管理员界面/管理员管理*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","管理员管理");
        modelAndView.setViewName("admin/admin");
        return modelAndView;
    }
    @GetMapping("/nurseManage")
    public ModelAndView nurseManage(){
        log.info("********管理员界面/护士管理*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","护士管理");
        modelAndView.setViewName("admin/nurses");
        return modelAndView;
    }
    @GetMapping("/userManage")
    public ModelAndView userManage(){
        log.info("********管理员界面/用户管理*********");
        List<Users> users;
        users = usersService.checkAllUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","用户管理");
//        modelAndView.addObject("username",userIDNum.substring(0,4));
        if (users != null && !users.isEmpty()){
            modelAndView.addObject("users",users);
            log.info("[{}]",users);
        }
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }
    @GetMapping("/medicineManage")
    public ModelAndView medicineManage(){
        log.info("********管理员界面/药品管理*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","药品管理");
        modelAndView.setViewName("admin/medicine");
        return modelAndView;
    }
    @GetMapping("/departmentManage")
    public ModelAndView departmentManage(){
        log.info("********管理员界面/科室管理*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","科室管理");
        modelAndView.setViewName("admin/department");
        return modelAndView;
    }
}
