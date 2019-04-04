package com.myHospital.hospital.controller;

import com.myHospital.hospital.dao.UsersDao;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/3
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private RecordService recordService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PrescriptionRecordService prescriptionRecordService;

    @Autowired
    private MedicineDepartmentService medicineDepartmentService;

    @GetMapping("/showRecordPage")
    public ModelAndView showRecordPage(String userId){
        log.info("********医生界面/预约就诊*********");
        ModelAndView modelAndView = new ModelAndView();
        Users users = usersService.findUserByID(userId);
        modelAndView.addObject("users",users);log.info("[{}]",users);
        List<Medicine> medicines = medicineDepartmentService.findAllMedicine();
        modelAndView.addObject("medicines",medicines);log.info("[{}]",medicines);
//        List<Record> records = prescriptionRecordService.findAllRecordAndPrescription(userId);//病历
//        modelAndView.addObject("records",records);log.info("[{}]",records);

        List<Prescription> prescriptions = new ArrayList<>();
        modelAndView.addObject("prescriptions",prescriptions);
        modelAndView.setViewName("doctor/record");
        return modelAndView;
    }

//    @PostMapping("/addRecordAndPrescription")
//    public ModelAndView addRecordAndPrescription(@ModelAttribute List<Prescription> prescriptions, @RequestParam String userId,
//                                                 @RequestParam String recordContent, @RequestParam String advice){
//        log.info("********添加病历和处方*********");
//        Session session = SecurityUtils.getSubject().getSession();
//        Users user = (Users) session.getAttribute("USER_SESSION");
//        Doctors doctors = doctorService.findDoctorByUserId(user.getUserId());
//        Record record1 = new Record();
//        record1.setUserId(userId);
//        record1.setDoctorId(doctors.getDoctorId());
//        prescriptionRecordService.addRecordAndPrescription(prescriptions,record1);
//        List<Record> record = prescriptionRecordService.findAllRecordAndPrescription(userId);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("title","查看病历信息");
//        modelAndView.addObject("username",user.getUserName());
//        return modelAndView;
//    }

    @GetMapping("/currentAppointment")
    public ModelAndView currentAppointment(){
        log.info("********医生界面/今日预约*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        Doctors doctors = doctorService.findDoctorByUserId(user.getUserId());
        List<Appointment> appointments = recordService.findTodayAppointment(doctors.getDoctorId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("appointments",appointments);log.info("[{}]",appointments);
        modelAndView.setViewName("doctor/current_appointment");
        return modelAndView;
    }

    @GetMapping("/allAppointment")
    public ModelAndView allAppointment(){
        log.info("********医生界面/近期预约*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        Doctors doctors = doctorService.findDoctorByUserId(user.getUserId());
        List<Appointment> appointments = recordService.findRecentAppointment(doctors.getDoctorId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("appointments",appointments);log.info("[{}]",appointments);
        modelAndView.setViewName("doctor/all_appointment");
        return modelAndView;
    }
}
