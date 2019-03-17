package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.RecordService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/current_appointment")
    public ModelAndView currentAppointment(){
        log.info("********医生界面/今日预约*********");
        Session session = SecurityUtils.getSubject().getSession();
        Users users = (Users) session.getAttribute("USER_SESSION");
        List<Appointment> appointments = recordService.findTodayAppointment(users.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","今天预约");
        if (appointments != null && !appointments.isEmpty()){
            modelAndView.addObject("appointments",appointments);
            log.info("[{}]",appointments);
        }
        modelAndView.setViewName("/doctor/current_appointment");
        return modelAndView;
    }
}
