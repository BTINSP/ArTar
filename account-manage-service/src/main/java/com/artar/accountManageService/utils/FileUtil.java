package com.artar.accountManageService.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;
@Slf4j
public class FileUtil {

    public static File getResourceLocalPath(){
        //  获取本地jar包绝对地址
        File localPath = new ApplicationHome().getDir();
        File resourcePath = new File(localPath.getPath() + "/resource");
        if (!resourcePath.exists()){
            if (!resourcePath.mkdir()){
                //  创建失败
                log.error("创建resource文件夹失败");
                return null;
            }
        }
        return resourcePath;
    }

    public static File getAccountLocalPath(){
        File resourceLocalPath = getResourceLocalPath();
        if (resourceLocalPath == null){
            return null;
        }
        File accountPath = new File(resourceLocalPath.getPath() + "/account");
        //  不存在文件夹创建
        if (!accountPath.exists()){
            if (!accountPath.mkdir()){
                //  创建失败
                log.error("创建account文件夹失败");
                return null;
            }
        }
        return accountPath;

    }

}
