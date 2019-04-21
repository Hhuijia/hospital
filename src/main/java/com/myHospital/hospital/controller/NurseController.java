package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.*;
import com.myHospital.hospital.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/4/6
 */
@Controller
@RequestMapping("/nurse")
public class NurseController {
    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private PrescriptionRecordService prescriptionRecordService;

    @Autowired
    private MedicineDepartmentService medicineDepartmentService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CommonService commonService;

    @PostMapping("/showPrescriptionWithoutPay")
    @ResponseBody
    public List<Prescription> showPrescriptionWithoutPay(@RequestParam String userIDNum){
        log.info("********护士界面/病患缴费*********");
        Users users = usersService.findUserByIDNum(userIDNum);
        log.info("********[{}]*********",users);
        List<Record> records = prescriptionRecordService.findRecordAndPrescription(users.getUserId(),1);
        List<Prescription> prescriptions = new ArrayList<>();
        for (Record record : records){
            prescriptions.addAll(record.getPrescriptions());
            log.info("********[{}]*********",prescriptions);
        }
        return prescriptions;
    }

    @GetMapping("/showPayPage")
    public ModelAndView showPayPage(){
        log.info("********护士界面/缴费界面*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("nurse/pay");
        return modelAndView;
    }

    @PostMapping("/confirmPay")
    @ResponseBody
    public boolean confirmPay(@RequestParam String userIDNum, @RequestParam BigDecimal sum){
        log.info("********护士界面/确认缴费*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        Nurses nurses = nurseService.findNurseByUserId(user.getUserId());
        Users users = usersService.findUserByIDNum(userIDNum);
        List<Record> records = prescriptionRecordService.findRecordAndPrescription(users.getUserId(),1);
        for (Record record : records){
            String recordId = record.getPrescriptions().get(0).getRecordId();
            int i = prescriptionRecordService.updateRecordStatusById(2,recordId);//待配药
            log.info("[{}]",i);
            Pay pay = new Pay();
            pay.setPayCount(sum);
            pay.setUserId(record.getUserId());
            pay.setNurseId(nurses.getNurseId());
            pay.setRecordId(recordId);
            nurseService.addPay(pay);
        }
        return true;
    }

    @GetMapping("/showGetMedicinePage")
    public ModelAndView showGetMedicinePage(){
        log.info("********护士界面/取药界面*********");
        ModelAndView modelAndView = new ModelAndView();
        List<Pay> pays = nurseService.finAllPay();
        for (Pay pay : pays){
            String userName = usersService.findUserByID(pay.getUserId()).getUserName();
            pay.setUserName(userName);
        }
        modelAndView.addObject("pays", pays);
        modelAndView.setViewName("nurse/getMedicine");
        return modelAndView;
    }

    @GetMapping("/showPrescriptionDetail")
    public ModelAndView showPrescriptionDetail(@RequestParam String recordId, @RequestParam String type){
        log.info("********护士界面/处方详情界面*********");
        ModelAndView modelAndView = new ModelAndView();
        boolean isShow = false;
        if (type.equals("check")){
            isShow = true;
        }else {
            int i = prescriptionRecordService.updateRecordStatusById(3,recordId);//正在配药
            log.info("[{}]",i);
        }
        List<Prescription> prescriptions = prescriptionRecordService.findPrescriptionById(recordId);
        modelAndView.addObject("prescriptions", prescriptions);
        modelAndView.addObject("isShow",isShow);
        modelAndView.setViewName("nurse/prescriptionDetail");
        return modelAndView;
    }

    @GetMapping("/confirmGetMedicine")
    public ModelAndView confirmGetMedicine(@RequestParam String recordId){
        log.info("********护士界面/确认取药*********");
        int i = prescriptionRecordService.updateRecordStatusById(4,recordId);//完成配药
        log.info("[{}]",i);
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        Nurses nurses = nurseService.findNurseByUserId(user.getUserId());
        Record record = prescriptionRecordService.findRecordById(recordId);
        GetMedicine getMedicine = new GetMedicine();
        getMedicine.setNurseId(nurses.getNurseId());
        getMedicine.setUserId(record.getUserId());
        getMedicine.setRecordId(recordId);
        nurseService.addGetMedicine(getMedicine);
        return new ModelAndView("redirect:showGetMedicinePage");
    }

    @GetMapping("/makeAppointment")
    public ModelAndView makeAppointment(){
        log.info("********护士界面/预约挂号*********");
        ModelAndView modelAndView = new ModelAndView();
        List<Department> departments = medicineDepartmentService.findAllDepartment();
        modelAndView.addObject("departments",departments);
        modelAndView.setViewName("nurse/makeAppointment");
        return modelAndView;
    }

    @GetMapping("/choiceDoctor")
    public ModelAndView choiceDoctor(@RequestParam String departmentId){
        log.info("********护士界面/选择医生*********");
        ModelAndView modelAndView = new ModelAndView();
        Department department = medicineDepartmentService.findDepartmentById(departmentId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<Schedule> schedules = scheduleService.findScheduleCurrentDay(sdf.format(new Date()),department.getDepartmentName());
        modelAndView.addObject("schedules",schedules);
        modelAndView.setViewName("nurse/choiceDoctor");
        return modelAndView;
    }

    @GetMapping("/loadUser")
    public ModelAndView loadUser(@RequestParam String scheduleId){
        log.info("********护士界面/载入病患*********");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("scheduleId",scheduleId);
        modelAndView.setViewName("nurse/loadUser");
        return modelAndView;
    }

    @PostMapping("/findUser")
    @ResponseBody
    public Users findUser(@RequestParam String userIDNum){
        log.info("********护士界面/查找病患*********");
        Users users = usersService.findUserByIDNum(userIDNum);
        return users;
    }

    @PostMapping("/confirmAppointment")
    @ResponseBody
    public boolean confirmAppointment(@RequestParam String userIDNum, @RequestParam String scheduleId){
        log.info("********护士界面/已注册用户确定预约*********");
        Users users = usersService.findUserByIDNum(userIDNum);
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        Department department = medicineDepartmentService.findDepartmentByName(schedule.getDepartmentName());
        String time = "";
        switch (schedule.getWorkTime()){
            case 1:
                time = "09:00:00";break;
            case 2:
                time = "10:00:00";break;
            case 3:
                time = "11:00:00";break;
            case 4:
                time = "14:00:00";break;
            case 5:
                time = "15:00:00";break;
            case 6:
                time = "16:00:00";break;
            default:
                break;
        }
        String appointmentTime = schedule.getWorkDate().toString()+" "+time;
        Appointment appointment = new Appointment();
        appointment.setUserId(users.getUserId());
        appointment.setDoctorId(schedule.getDoctorId());
        appointment.setDepartmentId(department.getDepartmentId());
        appointment.setAppointmentTime(appointmentTime);
        usersService.makeAppointment(appointment,scheduleId);
        return true;
    }

    @PostMapping("/addU")
    public ModelAndView addU(@RequestParam String scheduleId, @RequestParam String userName,
                             @RequestParam String userPhone, @RequestParam String userIDNum){
        log.info("********护士界面/未注册用户确定预约*********");
        Users users = new Users();
        users.setUserName(userName);
        users.setUserPwd(userIDNum.substring(userIDNum.length()-6));
        users.setUserSex("男");
        users.setUserAge(18);
        users.setUserIDNum(userIDNum);
        users.setUserPhone(userPhone);
        users.setUserAddress("未填写");
        List<String> roleIds = new ArrayList<>();
        roleIds.add("ROLE_0338_1552723369168");
        commonService.add(users,null,roleIds,"user");
        confirmAppointment(userIDNum,scheduleId);
        return new ModelAndView("redirect:makeAppointment");
    }

    @GetMapping("/todayGetMedicine")
    public ModelAndView todayGetMedicine(){
        log.info("********护士界面/今日配药记录*********");
        ModelAndView modelAndView = new ModelAndView();
        List<GetMedicine> getMedicines = nurseService.findAllGetMedicineToday();
        modelAndView.addObject("getMedicines",getMedicines);
        modelAndView.setViewName("nurse/todayGetMedicine");
        return modelAndView;
    }
}
