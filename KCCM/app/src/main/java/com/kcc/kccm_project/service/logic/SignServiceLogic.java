package com.kcc.kccm_project.service.logic;

import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.service.SignService;
import com.kcc.kccm_project.store.SignStore;
import com.kcc.kccm_project.store.logic.StoreFactoryLogic;
import com.kcc.kccm_project.util.ConversionUtil;
import com.kcc.kccm_project.util.signUtill.InvalidValueException;

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
        // TODO: userINfo에 있는 내용중 유효하지 않는 데이터가 있을 경우 예외 처리 발생 기능 구현
        if ( isValidEmail(userInfo.getEmail()) && isValidPassword(userInfo.getPassword()) )
            return signStore.create(userInfo);

        else
            throw new InvalidValueException("Invalid Email or password");
    }


    @Override
    public UserInfo findUser(String schoolNumer)
    {
        return null;
    }

    @Override
    public void modifyUser(UserInfo userInfo)
    {
        return;
    }

    @Override
    public void removeUser(String schoolNumber)
    {
        return;
    }

    private boolean isValidEmail(String inputEmail)
    {
        // e-mail regular expression: check input value is whether e-mail-form or not
        final Pattern VALID_EMIAL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMIAL_ADDRESS_REGEX.matcher(inputEmail);

        // return true if it's e-mail-form, else return false
        return matcher.find();
    }

    private boolean isValidPassword(String inputPW)
    {
        // password regular expression: check input value is 8 to 15 length, and only alpha-numeric-form
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(inputPW);

        // return true if it's correct-password-form, else return false
        return matcher.find();
    }

} // end class SignServiceLogic
