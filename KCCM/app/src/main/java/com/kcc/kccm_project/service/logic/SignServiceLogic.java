package com.kcc.kccm_project.service.logic;

import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.service.SignService;
import com.kcc.kccm_project.store.SignStore;
import com.kcc.kccm_project.store.logic.StoreFactoryLogic;
// import com.kcc.kccm_project.util.ConversionUtil;
import com.kcc.kccm_project.util.signUtill.InvalidValueException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignServiceLogic implements SignService
{
    private SignStore signStore;

    public SignServiceLogic()
    {
        signStore = StoreFactoryLogic.getInstance().getSignStore();
    }

    @Override
    public String registerUser(UserInfo userInfo)
    {
        // TODO: userInfo에 있는 내용 중 유효하지 않은 데이터가 있을 경우 예외 처리 발생 기능 구현
        if ( isValidEmail(userInfo.getEmail()) && isValidPassword(userInfo.getPassword()) )
            return signStore.create(userInfo);

        else
            throw new InvalidValueException("Invalid Email or Password");
    }


    @Override
    public UserInfo findUser(String schoolNumber)
    {
        // TODO: 관리자가 사용하는 기능/ 친구 찾기 기능에서도 사용할 수 있음
        return null;
    }

    @Override
    public void modifyUser(UserInfo userInfo)
    {
        //TODO: 사용자가 회원정보를 변경하려고 할 때 접근하는 메소드
        return;
    }

    @Override
    public void removeUser(String schoolNumber)
    {
        //TODO: 유저정보를 삭제하는 기능
        return;
    }

    @Override
    public String isValid(String email, String password) {
        if(isValidEmail(email) && isValidPassword(password)) {
            return "OK";
        } else {
            throw new InvalidValueException("Invalid email or password");
        }
    }

    private boolean isValidEmail(String inputEmail)
    {
        // e-mail regular expression: check input value is whether e-mail-form or not
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(inputEmail);

        // return true if it's e-mail-form, else return false
        return matcher.find();
    }

    private boolean isValidPassword(String inputPW)
    {
        // password regular expression: check input value is 8 to 15 length, and only alpha-numeric-form
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,20})");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(inputPW);

        // return true if it's correct-password-form, else return false
        return matcher.find();
    }

} // end class SignServiceLogic
