package com.example.coursedesign.Untils;

import com.example.coursedesign.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUntil {
    public static AccountProfile getprofile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
