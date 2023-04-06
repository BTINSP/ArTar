package com.artar.accountManageService.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String allAccount(){
        return "account";
    }

}
