package com.kcc.kccm_project.controller;

import com.android.volley.RequestQueue;
import com.google.protobuf.NullValue;
import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.service.SignService;
import com.kcc.kccm_project.service.logic.ServiceFactoryLogic;
import com.kcc.kccm_project.util.signUtill.NoSuchUserException;
import com.kcc.kccm_project.util.signUtill.NullValueException;

public class SignController
{
    private SignService signService;
    RequestQueue queue;

    public SignController()
    {
        this.signService = ServiceFactoryLogic.getInstance().createSignService();
    }

    //회원 가입을 하는 데 있어서 빈 문서가 있을 경우 에러 처리
    public String signUp(UserInfo userInfo)
    {
        if(userInfo.getEmail() == null) {
            throw new NullValueException("email value is null");
        }

        if(userInfo.getPassword() == null) {
            throw new NullValueException("password value is null");
        }

        if(userInfo.getSchoolNumber() == null) {
            throw new NullValueException("school number value is null");
        }

        if(userInfo.getDepartment() == null) {
            throw new NullValueException("department value is null");
        }

        if(userInfo.getName() == null) {
            throw new NullValueException("name value is null");
        }

        String response = signService.registerUser(userInfo);
        return response;
    }

    public String signIn(String email, String password) {
        if(email == null) {
            throw new NullValueException("email value is null");
        }

        if(password == null) {
            throw new NullValueException("password value is null");
        }

        return "OK";
    }
    
} // end class SignController
