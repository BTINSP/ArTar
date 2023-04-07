package com.artar.accountManageService.controller;


import com.artar.accountManageService.component.Result;
import com.artar.accountManageService.mapper.AccountMapper;
import com.artar.accountManageService.service.AccountControllerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage/account")
public class AccountController {

    private final AccountControllerService accountControllerService;
    public AccountController(AccountControllerService accountControllerService) {
        this.accountControllerService = accountControllerService;
    }

    @GetMapping("getAllAccount")
    public Result allAccount(){
        return accountControllerService.getAllAccount();
    }

    @PostMapping("createEthAccount")
    public Result createEthAccount(@RequestParam("count")Integer count){
        return accountControllerService.createEthAccount(count);
    }

}
