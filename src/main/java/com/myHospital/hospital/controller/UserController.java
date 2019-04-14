package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.*;
import com.myHospital.hospital.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UsersService usersService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private MedicineDepartmentService medicineDepartmentService;

    @Autowired
    private PrescriptionRecordService prescriptionRecordService;

    /**
     * 按科室查询
     * @param msg 医生ID+科室ID+预约时间
     * @return 确定预约信息界面
     */
    @PostMapping("/ShowAppointmentPage")
    @ResponseBody
    public ModelAndView ShowAppointmentPage(@RequestParam String msg){
        log.info("********用户界面/预约*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        String[] msgs = msg.split(",");
        Department department = medicineDepartmentService.findDepartmentByName(msgs[1]);
        Doctors doctor = doctorService.findDoctorById(msgs[0]);
        String appointTime = "";
        switch (msgs[3]){
            case "9:00-10:00":
                appointTime = "09:00:00";
                break;
            case "10:00-11:00":
                appointTime = "10:00:00";
                break;
            case "11:00-12:00":
                appointTime = "11:00:00";
                break;
            case "14:00-15:00":
                appointTime = "14:00:00";
                break;
            case "15:00-16:00":
                appointTime = "15:00:00";
                break;
            case "16:00-17:00":
                appointTime = "16:00:00";
                break;
            default:
                break;
        }
        String appoint = msgs[2]+" "+appointTime;
        log.info("********after-[{}]*********",appoint);
        modelAndView.addObject("title","预约");
        modelAndView.addObject("user", user);
        modelAndView.addObject("department",department);
        modelAndView.addObject("doctor",doctor);
        modelAndView.addObject("appoint",appoint);
        modelAndView.addObject("scheduleId",msgs[4]);
        modelAndView.addObject("appointment",new Appointment());
        modelAndView.setViewName("user/makeAppointment");
        return modelAndView;
    }

    /**
     * 添加预约
     * @param userId 用户ID
     * @param doctorId 医生ID
     * @param departmentId 科室ID
     * @param appointmentTime 预约时间
     * @return 我的预约界面
     */
    @GetMapping("/makeAppointment")
    public ModelAndView makeAppointment(@RequestParam String userId, @RequestParam String doctorId,
                                        @RequestParam String departmentId, @RequestParam String appointmentTime, @RequestParam String scheduleId){
        log.info("********用户界面/添加预约*********");
        Appointment appointment = new Appointment();
        appointment.setUserId(userId);
        appointment.setDoctorId(doctorId);
        appointment.setDepartmentId(departmentId);
        appointment.setAppointmentTime(appointmentTime);
        usersService.makeAppointment(appointment,scheduleId);
        return new ModelAndView("redirect:checkMyAppointment");
    }

    /**
     * 取消预约
     * @param appointmentId 预约Id
     * @return 个人预约界面
     */
    @GetMapping("/cancelAppointment")
    public ModelAndView cancelAppointment(@RequestParam String appointmentId){
        log.info("********用户界面/取消预约*********");
        usersService.updateStatusById(appointmentId,2);
        return new ModelAndView("redirect:checkMyAppointment");
    }

    /**
     * 查看我的预约
     * @return 个人预约界面
     */
    @GetMapping("/checkMyAppointment")
    public ModelAndView checkMyAppointment(){
        log.info("********用户界面/查看我的预约*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","查看我的预约");
        List<Appointment> appointments = usersService.findAllAppointmentOfOneByUserId(user.getUserId());
        modelAndView.addObject("appointments", appointments);
        modelAndView.setViewName("user/myAppointment");
        return modelAndView;
    }

    /**
     * 查看个人信息
     * @return 个人信息页面
     */
    @GetMapping("/checkMyInfo")
    public ModelAndView checkMyInfo(){
        log.info("********用户界面/查看个人信息*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","查看个人信息");
//        Users users = usersService.findUserByID(user.getUserId());
        modelAndView.addObject("users", user);
        modelAndView.setViewName("user/myPage");
        return modelAndView;
    }

    /**
     * 更新个人信息
     * @param userMap 用户信息Map
     * @return 更新后的用户信息Map
     */
    @PostMapping("/updateMyInfo")
    @ResponseBody
    public Map<String,String> updateMyInfo(@RequestBody Map<String,String> userMap){
        log.info("********用户界面/更新个人信息*********");
        log.info("********[{}]*********",userMap);
        Users users = new Users();
        users.setUserName(userMap.get("userName"));
        users.setUserAge(Integer.parseInt(userMap.get("userAge")));
        users.setUserSex(userMap.get("userSex"));
        users.setUserPhone(userMap.get("userPhone"));
        users.setUserAddress(userMap.get("userAddress"));
        users.setUserIDNum(userMap.get("userIDNum"));
        int result = usersService.updateUserByIdNum(users);
        log.info("********[{}]*********",result);
        Users userAfterUpdate = usersService.findUserByIDNum(userMap.get("userIDNum"));
        Map<String,String> userAfterUpdateMap = new HashMap<String,String>();
        userAfterUpdateMap.put("userName",userAfterUpdate.getUserName());
        userAfterUpdateMap.put("userAge",userAfterUpdate.getUserAge().toString());
        userAfterUpdateMap.put("userSex",userAfterUpdate.getUserSex());
        userAfterUpdateMap.put("userPhone",userAfterUpdate.getUserPhone());
        userAfterUpdateMap.put("userAddress",userAfterUpdate.getUserAddress());
        return userAfterUpdateMap;
    }

    @GetMapping("/checkMyRecord")
    public ModelAndView checkMyRecord(){
        log.info("********用户界面/查看历史病历*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        List<Record> records = prescriptionRecordService.findRecordAndPrescription(user.getUserId(),4);
        for (Record record : records){
            String doctorName = doctorService.findDoctorById(record.getDoctorId()).getDoctorName();
            record.setDoctorId(doctorName);
        }
        modelAndView.addObject("records", records);
        modelAndView.setViewName("user/myRecord");
        return modelAndView;
    }

}