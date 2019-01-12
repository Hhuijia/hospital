package com.myHospital.hospital.controller;

import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.mapper.UsersDao;
import com.myHospital.hospital.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResignUser {

    @PostMapping("/resign")
    public String resignUser(Model model) {
        model.addAttribute("users", new Users());
        return "resign";
    }

    @PostMapping("/resign")
    public String addUser(@ModelAttribute Users users){

        return "/login";
    }
}
