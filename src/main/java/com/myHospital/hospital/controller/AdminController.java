package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.*;
import com.myHospital.hospital.service.*;
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
    private CommonService commonService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/doctorManage")
    public ModelAndView doctorManage(){
        log.info("********管理员界面/医生管理*********");
        List<Doctors> doctors = commonService.findAll("doctor");;
        List<String> departmentName= departmentService.findAlldepartmentName();
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
        users.setUserPwd("huanghuijia");
        Doctors doctors = new Doctors();
        doctors.setDoctorName(users.getUserName());
        doctors.setDoctorTitle(doctorTitle);
        doctors.setDoctorProfession(doctorProfession);
        doctors.setDoctorMedicalServiceLife(doctorMedicalServiceLife);
        doctors.setDoctorIntroduction(doctorIntroduction);
        doctors.setDepartmentName(departmentName);
        commonService.add(users,doctors,"doctor");
        return new ModelAndView("redirect:doctorManage");
    }
    @GetMapping("/delDoctor")
    public ModelAndView delDoctor(@RequestParam String doctorId){
        log.info("********删除医生*********");
        commonService.delete(doctorId,"doctor");
        return new ModelAndView("redirect:doctorManage");
    }
    @GetMapping("/editDoctor")
    public ModelAndView editDoctor(@RequestParam String doctorId){
        log.info("********删除医生*********");
        return new ModelAndView("redirect:doctorManage");
    }


    @GetMapping("/adminManage")
    public ModelAndView adminManage(){
        log.info("********管理员界面/管理员管理*********");
        List<Admins> admins = commonService.findAll("admin");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","管理员管理");
        if (admins != null && !admins.isEmpty()){
            modelAndView.addObject("admins",admins);
            log.info("[{}]",admins);
        }
        modelAndView.addObject("users",new Users());
        modelAndView.setViewName("admin/admins");
        return modelAndView;
    }
    @PostMapping("/addAdmin")
    public ModelAndView addAdmin(@ModelAttribute Users users, @RequestParam String adminTitle){
        log.info("********添加管理员*********");
        users.setUserPwd("huanghuijia");
        Admins admins = new Admins();
        admins.setAdminName(users.getUserName());
        admins.setAdminTitle(adminTitle);
        commonService.add(users,admins,"admin");
        return new ModelAndView("redirect:adminManage");
    }
    @GetMapping("/delAdmin")
    public ModelAndView delAdmin(@RequestParam String adminId){
        log.info("********删除管理员*********");
        commonService.delete(adminId,"admin");
        return new ModelAndView("redirect:adminManage");
    }
    @GetMapping("/editAdmin")
    public ModelAndView editAdmin(@RequestParam String adminId){
        log.info("********编辑管理员*********");
        return new ModelAndView("redirect:adminManage");
    }


    @GetMapping("/nurseManage")
    public ModelAndView nurseManage(){
        log.info("********管理员界面/护士管理*********");
        List<Nurses> nurses = commonService.findAll("nurse");
        List<String> departmentName = departmentService.findAlldepartmentName();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","护士管理");
//        modelAndView.addObject("username",userIDNum.substring(0,4));
        if (nurses != null && !nurses.isEmpty()){
            modelAndView.addObject("nurses",nurses);
            log.info("[{}]",nurses);
        }
        if (departmentName != null && !departmentName.isEmpty()){
            modelAndView.addObject("departmentName",departmentName);
            log.info("[{}]",departmentName);
        }
        modelAndView.addObject("users",new Users());
        modelAndView.setViewName("admin/nurses");
        return modelAndView;
    }
    @PostMapping("/addNurse")
    public ModelAndView addNurse(@ModelAttribute Users users, @RequestParam String nurseTitle, @RequestParam String nursePlace){
        log.info("********添加护士*********");
        users.setUserPwd("huanghuijia");
        Nurses nurses = new Nurses();
        nurses.setNurseName(users.getUserName());
        nurses.setNurseTitle(nurseTitle);
        nurses.setNursePlace(nursePlace);
        commonService.add(users,nurses,"nurse");
        return new ModelAndView("redirect:nurseManage");
    }
    @GetMapping("/delNurse")
    public ModelAndView delNurse(@RequestParam String nurseId){
        log.info("********删除护士*********");
        commonService.delete(nurseId,"nurse");
        return new ModelAndView("redirect:nurseManage");
    }
    @GetMapping("/editNurse")
    public ModelAndView editNurse(@RequestParam String nurseId){
        log.info("********编辑护士*********");
        return new ModelAndView("redirect:nurseManage");
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


    @GetMapping("/rolePermissionManage")
    public ModelAndView rolePermissionManage(){
        log.info("********管理员界面/角色权限管理*********");
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissions = rolePermissionService.findAllPermission();
        List<Role> roles = rolePermissionService.findAllRole();
        modelAndView.addObject("title","角色权限管理");
        if (permissions != null && !permissions.isEmpty()){
            modelAndView.addObject("permissions",permissions);
            log.info("[{}]",permissions);
        }
        if (roles != null && !roles.isEmpty()){
            modelAndView.addObject("roles",roles);
            log.info("[{}]",roles);
        }
        modelAndView.setViewName("admin/rolePermission");
        return modelAndView;
    }
    @PostMapping("/addRole")
    public ModelAndView addRole(@RequestParam String roleName, @RequestParam List<String> permissionIds){
        log.info("********管理员界面/角色管理*********");
        Role role = new Role();
        role.setRoleName(roleName);
        rolePermissionService.addRole(role,permissionIds);
        return new ModelAndView("redirect:rolePermissionManage");
    }
    @PostMapping("/addPermission")
    public ModelAndView addPermission(@RequestParam String permissionName){
        log.info("********管理员界面/角色管理*********");
        Permission permission = new Permission();
        permission.setPermissionName(permissionName);
        rolePermissionService.addPermission(permission);
        return new ModelAndView("redirect:rolePermissionManage");
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
