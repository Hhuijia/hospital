package com.myHospital.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author QUEENEY
 * @date 2019/3/3
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @PostMapping("/index")
    public ModelAndView show(){
        return new ModelAndView("doctor/doctor_index");
    }
}
