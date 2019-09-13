package com.kcc.kccm_project.store.logic;

import com.google.firebase.firestore.FirebaseFirestore;
import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.store.SignStore;

public class SignStoreLogic implements SignStore
{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public String create(UserInfo userInfo)
    {
        db.collection("UserInfo")
                .add(userInfo);

        return "OK";
    }

    @Override
    public UserInfo retrieve(String schoolNumber)
    {
        return null;
    }

    @Override
    public void update(UserInfo userInfo)
    {
        // TODO
    }

    @Override
    public void delete(String schoolNumber)
    {
        // TODO
    }
}
