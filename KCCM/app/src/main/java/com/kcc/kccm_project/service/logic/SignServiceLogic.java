package com.kcc.kccm_project.service.logic;

import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.service.SignService;
import com.kcc.kccm_project.store.SignStore;
import com.kcc.kccm_project.store.logic.StoreFactoryLogic;
import com.kcc.kccm_project.util.ConversionUtil;

public class SignServiceLogic implements SignService
{
    private SignStore signStore;
    private String url = "https://us-central1-mobile-kcc.cloudfunctions.net/signup";

    public SignServiceLogic()
    {
        signStore = StoreFactoryLogic.getInstance().getSignStore();
    }

    @Override
    public String registerUser(UserInfo userInfo)
    {
        /*
        if(userInfo.getSchoolNumber() == null) {
            throw new NoSuchUserException("Invalid user info");
        }*/
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
