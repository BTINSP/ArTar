package com.artar.accountManageService.mapper;

import com.artar.accountManageService.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountMapper {

    List<Account> getAllAccount();

    boolean addEthAccountList(@Param("accountList")List<Account> accountList);
}
