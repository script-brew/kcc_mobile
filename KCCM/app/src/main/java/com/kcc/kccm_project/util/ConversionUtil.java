package com.kcc.kccm_project.util;

import com.kcc.kccm_project.Entity.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class ConversionUtil {
    public static String jsonToString(UserInfo userInfo) {
        try {
            JSONObject userInfoObject = new JSONObject();
            userInfoObject.put("user_id", userInfo.getEmail());
            userInfoObject.put("user_pw", userInfo.getPassword());
            userInfoObject.put("user_name", userInfo.getName());
            userInfoObject.put("user_birthday", userInfo.getBirthday());
            //userInfoObject.put("user_id", userInfo.getSchoolNumber());
            //userInfoObject.put("user_department", userInfo.getDepartment());
            //userInfoObject.put("user_signupdate", userInfo.getSignUpDate());

            JSONObject requestObject = new JSONObject();
            requestObject.put("user_info" , userInfoObject);

            return requestObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}

