package com.kcc.kccm_project.controller;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.service.SignService;
import com.kcc.kccm_project.service.logic.ServiceFactoryLogic;
import com.kcc.kccm_project.ui.RegisterActivity;
import com.kcc.kccm_project.util.NoSuchUserException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class SignController
{
    private SignService signService;
    RequestQueue queue;

    public SignController()
    {
        this.signService = ServiceFactoryLogic.getInstance().createSignService();
    }

    public String signUp(UserInfo userInfo)
    {
        if(userInfo.getEmail() == null) {
            throw new NoSuchUserException("xx");
        }

        String response = signService.registerUser(userInfo);
        return response;
    }
    
} // end class SignController
