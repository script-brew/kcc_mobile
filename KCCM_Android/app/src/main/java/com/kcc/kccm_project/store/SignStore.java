package com.kcc.kccm_project.store;

import com.kcc.kccm_project.Entity.UserInfo;

public interface SignStore
{
    public String create(UserInfo userInfo);
    public UserInfo retrieve(String schoolNumber);
    public void update(UserInfo userInfo);
    public void delete(String schoolNumber);
}
