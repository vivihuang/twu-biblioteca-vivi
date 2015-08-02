package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivi on 8/2/15.
 */
public class UserService {
    private List<UserAccount> userAccountList = new ArrayList<>();
    private List<UserAccount> allUserAccountList = addUserAccount();

    public List<UserAccount> addUserAccount(){
        UserAccount userAccount = new UserAccount("vivi","vivi@gmail.com",12345678,"123-4567","vivi");
        userAccountList.add(userAccount);
        return userAccountList;
    }

    public String checkLoginStatus(String inputMessage,List<UserAccount> userAccountList){
        List<String> inputUserAccountList = extractUserAccount(inputMessage);
        if (inputUserAccountList!=null) {
            String userNumber = inputUserAccountList.get(0);
            String userPassword = inputUserAccountList.get(1);
            for (UserAccount userAccount : userAccountList) {
                if (userAccount.getNumber().equals(userNumber) && userAccount.getPassword().equals(userPassword)) {
                    return userNumber;
                }
            }
        }
        return null;
    }

    public List<String> extractUserAccount(String inputMessage){
        List<String> userAccountList = new ArrayList<>();
        char[] charArray = inputMessage.toCharArray();
        for (int i=0;i<charArray.length;i++) {
            if (charArray[i] == ' ') {
                userAccountList.add(inputMessage.substring(0,i));
                userAccountList.add(inputMessage.substring(i+1));
                return userAccountList;
            }
        }
        return null;
    }

    public String showUserInfo(String userNumber) {
        for (UserAccount userAccount: allUserAccountList) {
            if (userAccount.getNumber().equals(userNumber)) {
                return "Name: "+userAccount.getName()+"\nEmail: "+userAccount.getEmail()+"\nPhone: "+userAccount.getNumber();
            }
        }
        return null;
    }
}
