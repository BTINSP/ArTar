package com.artar.accountManageService.task;

import com.artar.accountManageService.model.Account;
import com.artar.accountManageService.utils.AccountUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class CreateAccountTask implements Runnable{

    private static int count;
    private static File path;
    private static final List<Account> accountList = new ArrayList<>();

    private static final Lock lock = new ReentrantLock();

    public CreateAccountTask(Integer count, File path) {
        CreateAccountTask.count = count;
        CreateAccountTask.path = path;
    }

    public List<Account> getAccountList(){
        return CreateAccountTask.accountList;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){

            Account account = AccountUtil.crateEthAccount(path);
            if (account != null){
                lock.lock();
                count--;
                if (count < 0 ){
                    lock.unlock();
                    break;
                }
                accountList.add(account);
                lock.unlock();
            }

        }
    }
}
