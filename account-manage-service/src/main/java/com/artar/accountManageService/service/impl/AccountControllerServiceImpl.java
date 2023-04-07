package com.artar.accountManageService.service.impl;

import com.artar.accountManageService.component.Result;
import com.artar.accountManageService.mapper.AccountMapper;
import com.artar.accountManageService.model.Account;
import com.artar.accountManageService.service.AccountControllerService;
import com.artar.accountManageService.task.CreateAccountTask;
import com.artar.accountManageService.utils.AccountUtil;
import com.artar.accountManageService.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class AccountControllerServiceImpl implements AccountControllerService {
    private final AccountMapper accountMapper;
    public AccountControllerServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Result getAllAccount() {
        try {
            List<Account> allUser = accountMapper.getAllAccount();
            if (!allUser.isEmpty()){

                List<Object> result = new ArrayList<>();
                for (Account account : allUser) {
                    System.out.println(account);
                    HashMap<String, Object> entity = new HashMap<>();
                    entity.put("id",account.getId());
                    entity.put("address",account.getAddress());
                    entity.put("status", account.getStatus());
                    result.add(entity);
                }

                return Result.success(result);
            }
        } catch (Exception ignored){

        }
        return Result.fail(400,"fail",null);
    }

    @Override
    public Result createEthAccount(Integer count) {
        //  检查参数
        if (count <= 0 ){
            return Result.fail(400,"请输入正确的数量.",null);
        }
        if (count > 1000){
            return Result.fail(400,"最大数为1000.",null);
        }

        //  获取accountPath
        File accountLocalPath = FileUtil.getAccountLocalPath();
        if (accountLocalPath == null){
            return null;
        }

        //  实例化任务
        CreateAccountTask createAccountTask = new CreateAccountTask(100,accountLocalPath);
        //  创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        //  开始任务
        for (int i = 0; i < 8; i++) {
            executorService.submit(createAccountTask);
        }
        //  等待所有线程结束
        executorService.shutdown();
        while (!executorService.isTerminated()){}

        //  获取地址列表
        List<Account> accountList = createAccountTask.getAccountList();
        if (!accountList.isEmpty()){
            if (accountMapper.addEthAccountList(accountList)){
                return Result.success(200,"添加成功.", accountList);
            }
        }
        return Result.fail(400,"添加失败请重试.",null);

    }
}
