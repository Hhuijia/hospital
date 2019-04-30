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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    public ModelAndView showRecordPage(String userId,String appointmentId){
        log.info("********医生界面/预约就诊*********");
        ModelAndView modelAndView = new ModelAndView();
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        Doctors doctors = doctorService.findDoctorByUserId(user.getUserId());
        modelAndView.addObject("departmentName",doctors.getDepartmentName());
        Users users = usersService.findUserByID(userId);
        modelAndView.addObject("users",users);log.info("[{}]",users);
        modelAndView.addObject("appointmentId",appointmentId);log.info(appointmentId);
        modelAndView.addObject("currentDate",new Date());
        List<Medicine> medicines = medicineDepartmentService.findAllMedicine();
        modelAndView.addObject("medicines",medicines);log.info("[{}]",medicines);
        List<Record> records = prescriptionRecordService.findRecordAndPrescription(userId,4);
        for (Record record : records){
            String doctorName = doctorService.findDoctorById(record.getDoctorId()).getDoctorName();
            record.setDoctorId(doctorName);
        }
        modelAndView.addObject("records", records);
        List<Prescription> prescriptions = new ArrayList<>();
        modelAndView.addObject("prescriptions",prescriptions);
        modelAndView.setViewName("doctor/record");
        return modelAndView;
    }

    @GetMapping("/overTime")
    public ModelAndView overTime(String appointmentId){
        log.info("********医生界面/过号处理*********");
        usersService.updateStatusById(appointmentId,4);
        return new ModelAndView(("redirect:currentAppointment"));
    }

    @PostMapping("/addRecordAndPrescription")
    @ResponseBody
    public int addRecordAndPrescription(@RequestParam String appointmentId, @RequestParam String symptom, @RequestParam String diagnostic,
                                            @RequestParam String userId, @RequestParam String prescriptions){
        log.info("********添加病历和处方*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        Doctors doctors = doctorService.findDoctorByUserId(user.getUserId());

        Record record = new Record();
        record.setUserId(userId);
        record.setDoctorId(doctors.getDoctorId());
        record.setDiagnosticResult(diagnostic);
        record.setSymptom(symptom);

        List<Prescription> prescription = new ArrayList<>();
        prescriptions = prescriptions.substring(prescriptions.indexOf("[")+1,prescriptions.lastIndexOf("]"));
        String[] str = prescriptions.split("]");
        for (String string : str){
            Prescription pre = new Prescription();
            String[] prePart = string.substring(string.indexOf("[")+1).split(",");
            pre.setMedicineId(prePart[0].substring(1,prePart[0].length()-1));
            pre.setPrescriptionCount(Integer.parseInt(prePart[1].substring(1,prePart[1].length()-1)));
            pre.setPrescriptionUnit(prePart[2].substring(1,prePart[2].length()-1));
            pre.setDosageEachTime(BigDecimal.valueOf(Integer.parseInt(prePart[3].substring(1,prePart[3].length()-1))));
            pre.setPrescriptionDosage(prePart[4].substring(1,prePart[4].length()-1));
            pre.setPrescriptionUsage(prePart[5].substring(1,prePart[5].length()-1));

            prescription.add(pre);
        }
        log.info("[{}]",prescription);
        int isSuccess = prescriptionRecordService.addRecordAndPrescription(prescription,record);
        if (isSuccess == 1){
            usersService.updateStatusById(appointmentId,3);
        }
        return isSuccess;
    }

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
