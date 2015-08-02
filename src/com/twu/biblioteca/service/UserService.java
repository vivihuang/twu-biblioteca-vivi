package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.UserAccount;
import com.twu.biblioteca.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivi on 8/2/15.
 */
public class UserService {
    private List<UserInfo> userInfoList = new ArrayList<>();
    private List<UserAccount> userAccountList = new ArrayList<>();

    public List<UserInfo> addUserInfo(){
        UserInfo userInfo = new UserInfo("vivi","vivi@gmail.com",12345678,"123-4567");
        userInfoList.add(userInfo);
        return userInfoList;
    }

    public List<UserAccount> addUserAccount(){
        UserAccount userAccount = new UserAccount("123-4567","vivi");
        userAccountList.add(userAccount);
        return userAccountList;
    }
}
