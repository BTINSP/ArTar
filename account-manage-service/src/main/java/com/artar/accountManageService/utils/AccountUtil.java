package com.artar.accountManageService.utils;

import com.artar.accountManageService.model.Account;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;


public class AccountUtil {


    public static Account crateEthAccount(File path){
        try {
            //  创建钱包文件获取名称
            String fileName = WalletUtils.generateNewWalletFile("zhang1998", path);
            //  读取钱包文件获取 地址 公钥 私钥
            Credentials credentials = WalletUtils.loadCredentials("zhang1998", path.getPath() + "/" + fileName);
            String address = credentials.getAddress();
            String publicKey = "0x" + credentials.getEcKeyPair().getPublicKey().toString(16);
            String privateKey = "0x" + credentials.getEcKeyPair().getPrivateKey().toString(16);

            //  封装返回
            Account account = new Account();
            account.setAddress(address);
            account.setPublicKey(publicKey);
            account.setPrivateKey(privateKey);
            account.setMnemonic("null");
            account.setStatus(0);
            account.setFileName(fileName);
            account.setChain("ETH");

            return account;

        } catch (Exception e) {
            return null;
        }

    }
}
