package com.kcc.kccm_project.service.logic;

import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.service.SignService;
import com.kcc.kccm_project.store.SignStore;
import com.kcc.kccm_project.store.logic.StoreFactoryLogic;
import com.kcc.kccm_project.util.ConversionUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private boolean isEmailCorrect(String inputEmail)
    {
        // e-mail regular expression: check input value is whether e-mail-form or not
        final String emailRegularExpression = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i";
        Pattern pattern = Pattern.compile(emailRegularExpression);
        Matcher matched = pattern.matcher(inputEmail);

        // return true if it's e-mail-form, else return false
        return matched.find();
    }

    private boolean isPasswordCorrect(String inputPW)
    {
        // password regular expression: check input value is 8 to 15 length, and only alpha-numeric-form
        final String pwRegularExpression = "/^[A-Za-z0-9]{8,15}$/";
        Pattern pattern = Pattern.compile(pwRegularExpression);
        Matcher matched = pattern.matcher(inputPW);

        // return true if it's correct-password-form, else return false
        return matched.find();
    }

} // end class SignServiceLogic
