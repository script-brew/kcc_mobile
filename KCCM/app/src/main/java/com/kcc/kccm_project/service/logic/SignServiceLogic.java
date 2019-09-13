package com.kcc.kccm_project.service.logic;

import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.service.SignService;
import com.kcc.kccm_project.store.SignStore;
import com.kcc.kccm_project.store.logic.StoreFactoryLogic;
import com.kcc.kccm_project.util.ConversionUtil;

public class SignServiceLogic implements SignService
{
    private SignStore signStore;
    // private String url = "https://us-central1-mobile-kcc.cloudfunctions.net/signup";

    public SignServiceLogic()
    {
        signStore = StoreFactoryLogic.getInstance().getSignStore();
    }

    @Override
    public String registerUser(UserInfo userInfo)
    {
        //TODO: userINfo에 있는 내용중 유효하지 않는 데이터가 있을 경우 예외 처리 발생 기능 구현
        String response = signStore.create(userInfo);
        return response;
    }


    @Override
    public UserInfo findUser(String schoolNumer)
    {
        return null;
    }

    @Override
    public void modifyUser(UserInfo userInfo)
    {

    }

    @Override
    public void removeUser(String schoolNumber)
    {

    }
}
