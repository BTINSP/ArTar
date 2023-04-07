package com.artar.accountManageService.service;

import com.artar.accountManageService.component.Result;

public interface AccountControllerService {

    Result getAllAccount();

    Result createEthAccount(Integer count);
}
