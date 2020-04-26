package com.huajie.thinking.in.spring.bean.scope.web.controller;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    @Autowired
    private User user;

    public String index(Model model){
        model.addAttribute("user",user);
        return "index";
    }

}
