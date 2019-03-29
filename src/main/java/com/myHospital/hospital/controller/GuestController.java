package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.CommonService;
import com.myHospital.hospital.service.MedicineDepartmentService;
import com.myHospital.hospital.service.RolePermissionService;
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
 * @date 2019/3/29
 */
@Controller
@RequestMapping("/guest")
public class GuestController {
    private static final Logger log = LoggerFactory.getLogger(GuestController.class);

    @Autowired
    private MedicineDepartmentService medicineDepartmentService;

    @Autowired
    private CommonService commonService;

    /**
     * 获取表单值进行用户注册
     * @param users 用户
     * @return 登录界面
     */
    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute Users users){
        List<String> roleIds = new ArrayList<>();
        roleIds.add("ROLE_0338_1552723369168");
        commonService.add(users,null,roleIds,"user");
        return new ModelAndView("common/login");
    }

    /**
     * 用户点击注册
     * @return 注册界面
     */
    @GetMapping("/resign")
    public ModelAndView resign(){
        log.info("********注册*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","注册");
        modelAndView.addObject("user", new Users());
        modelAndView.setViewName("guest/resign");
        return modelAndView;
    }

    @GetMapping("/makeAppointmentByDepartment")
    public ModelAndView makeAppointmentByDepartment(){
        log.info("********按照科室预约*********");
        List<Department> departments = medicineDepartmentService.findAllDepartment();
        List<String> departmentSystems = medicineDepartmentService.findAllDepartmentSystem();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","科室");
        modelAndView.addObject("departments",departments);
        modelAndView.addObject("departmentSystems",departmentSystems);
        modelAndView.setViewName("guest/department");
        return modelAndView;
    }

    /**
     * 显示科室详情
     * @param departmentId 科室Id
     * @return 科室详情页面
     */
    @GetMapping("/showDepartmentDetail")
    public  ModelAndView showDepartmentDetail(@RequestParam String departmentId){
        log.info("********用户界面/科室详情*********");
        ModelAndView modelAndView = new ModelAndView();
        Department department = medicineDepartmentService.findDepartmentById(departmentId);
        modelAndView.addObject("title","科室详情");
        modelAndView.addObject("department",department);
        modelAndView.setViewName("guest/departmentDetail");
        return modelAndView;
    }

}
