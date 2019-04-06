package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.*;
import com.myHospital.hospital.service.NurseService;
import com.myHospital.hospital.service.PrescriptionRecordService;
import com.myHospital.hospital.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @PostMapping("/showPrescriptionWithoutPay")
    @ResponseBody
    public List<Prescription> showPrescriptionWithoutPay(String userIdNum){
        log.info("********护士界面/病患缴费*********");
        Users users = usersService.findUserByIDNum(userIdNum);
        List<Record> records = prescriptionRecordService.findRecordAndPrescription(users.getUserId(),"withoutPay");
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
    public boolean confirmPay(String userIdNum, BigDecimal sum){
        log.info("********护士界面/确认缴费*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users user = (Users) session.getAttribute("USER_SESSION");
        Nurses nurses = nurseService.findNurseByUserId(user.getUserId());
        Users users = usersService.findUserByIDNum(userIdNum);
        List<Record> records = prescriptionRecordService.findRecordAndPrescription(users.getUserId(),"withoutPay");
        for (Record record : records){
            int i = prescriptionRecordService.updateRecordStatusById(2,record.getRecordId());//待配药
            log.info("[{}]",i);
            Pay pay = new Pay();
            pay.setPayCount(sum);
            pay.setUserId(record.getUserId());
            pay.setNurseId(nurses.getNurseId());
            pay.setRecordId(record.getRecordId());
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
    public ModelAndView showPrescriptionDetail(String recordId){
        log.info("********护士界面/处方详情界面*********");
        ModelAndView modelAndView = new ModelAndView();
        int i = prescriptionRecordService.updateRecordStatusById(3,recordId);//正在配药
        log.info("[{}]",i);
        List<Prescription> prescriptions = prescriptionRecordService.findPrescriptionById(recordId);
        modelAndView.addObject("prescriptions", prescriptions);
        modelAndView.setViewName("nurse/prescriptionDetail");
        return modelAndView;
    }

    @PostMapping("/confirmGetMedicine")
    public ModelAndView confirmGetMedicine(String recordId){
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
}
