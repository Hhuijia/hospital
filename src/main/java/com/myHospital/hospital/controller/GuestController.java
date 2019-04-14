package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Department;
import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Schedule;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.myHospital.hospital.util.GetFeatureDayUtil.getFeatureDate;

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

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UsersService usersService;

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

    /**
     * 按照科室预约
     * @return 所有科室页面
     */
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

    /**
     * 所选科室排班
     * @param departmentId 科室ID
     * @return 所选科室排班页面
     */
    @GetMapping("/choiceDoctorAndDateTime")
    public ModelAndView choiceDoctorAndDateTime(@RequestParam String departmentId){
        log.info("********用户界面/选择医生时间*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","选择医生时间");
        Department department = medicineDepartmentService.findDepartmentById(departmentId);
        //按时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());
        log.info("*******[{}]-[{}]******",department.getDepartmentName(),currentDate);
        List<Doctors> doctors = doctorService.findDoctorToday(department.getDepartmentName(),currentDate);
        modelAndView.addObject("doctors",doctors);
        ArrayList<String> featureDaysList = new ArrayList<>();
        for (int i = 0; i <7; i++) {
            featureDaysList.add(getFeatureDate(i));
        }
        modelAndView.addObject("featureDaysList",featureDaysList);
        //按医生
        List<Doctors> doctors1 = doctorService.findDoctorInSameDepartment(department.getDepartmentName());
        modelAndView.addObject("doctors1",doctors1);

        modelAndView.addObject("department",department);
        modelAndView.setViewName("guest/schedule");
        return modelAndView;
    }

    /**
     * 加载当天值班医生的信息
     * @param currentDay 日期
     * @param departmentName 科室名称
     * @return 当天值班医生的信息
     */
    @PostMapping("/initDoctorMsg")
    @ResponseBody
    public List<Doctors> initDoctorMsg(@RequestParam String currentDay, @RequestParam String departmentName){
        log.info("********用户界面/加载医生信息*********");
        return doctorService.findDoctorToday(departmentName,currentDay);
    }

    @PostMapping("/initScheduleMsg")
    @ResponseBody
    public List<Schedule> initScheduleMsg(@RequestParam String currentDay, @RequestParam String doctorId){
        log.info("********用户界面/加载排班信息*********");
        log.info("********[{}]-[{}]*********",doctorId,currentDay);
        return scheduleService.findScheduleByDoctorId(doctorId,currentDay);
    }

    @PostMapping("/initScheduleMsgByDoctor")
    @ResponseBody
    public List<Schedule> initScheduleMsgByDoctor(@RequestParam String doctorId){
        log.info("********用户界面/加载排班信息*********");
        log.info("********[{}]-[{}]*********",doctorId);
        return scheduleService.findScheduleBetweenOneWeek(doctorId);
    }

    @PostMapping("/isExit")
    @ResponseBody
    public boolean isExit(String userIDNum){
        return usersService.findUserByIDNum(userIDNum) == null;
    }
}
