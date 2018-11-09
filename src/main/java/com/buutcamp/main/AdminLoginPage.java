package com.buutcamp.main;

import com.buutcamp.database.HibernateMethods;
import com.buutcamp.database.MessageData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminLoginPage {

    @GetMapping("/showLoginPage")
    public String showLoginPage(Model model){
        return "admin-login-page";
    }

    @GetMapping("/logoutdone")
    private String logout(){
        return "redirect:/";
    }
}