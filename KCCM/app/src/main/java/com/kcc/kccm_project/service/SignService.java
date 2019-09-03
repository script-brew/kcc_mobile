package com.kcc.kccm_project.service;

import com.kcc.kccm_project.Entity.UserInfo;

public interface SignService {
    public String registerUser(UserInfo userInfo);
    public UserInfo findUser(String schoolNumer);
    public void modifyUser(UserInfo userInfo);
    public void removeUser(String schoolNumber);
}
