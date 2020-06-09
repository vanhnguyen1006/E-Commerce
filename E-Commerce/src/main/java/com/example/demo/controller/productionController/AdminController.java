package com.example.demo.controller.productionController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminShow(){
        return "admin/index";
    }
}
