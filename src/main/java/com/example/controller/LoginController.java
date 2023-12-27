package com.example.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Auteur;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({"/", "/dashboard"})
@Api(value="Login Controller")
public class LoginController {

    /*@RequestMapping
    public String loginSubmit(){
        return "/pages/landing_page";
    }*/
    
    @GetMapping
    public ModelAndView getHomePage(Model model) {
        return new ModelAndView("/pages/landing_page", model.asMap());
    }
    

}
