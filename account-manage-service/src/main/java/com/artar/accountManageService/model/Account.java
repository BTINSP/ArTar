package com.artar.accountManageService.model;

import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String address;
    private String publicKey;
    private String privateKey;
    private String mnemonic;
    private Integer status;
    private String fileName;
    private String chain;

}
