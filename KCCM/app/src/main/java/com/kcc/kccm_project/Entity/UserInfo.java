package com.kcc.kccm_project.Entity;

import com.kcc.kccm_project.util.DateUtil;

public class UserInfo
{
    private String email;
    private String schoolNumber;
    private String password;
    private String name;
    private String department;
    private String birthday;
    private String signUpDate;


    public UserInfo()
    {
        this.signUpDate = DateUtil.today();
    }

    public UserInfo(String email, String schoolNumber, String password, String name, String department, String birthday)
    {
        this();
        this.email = email;
        this.schoolNumber = schoolNumber;
        this.password = password;
        this.name = name;
        this.department = department;
        this.birthday = birthday;
    }

    public UserInfo(String email, String password, String name, String birthday)
    {
        this.email = email;
        this.schoolNumber = schoolNumber;
        this.password = password;
        this.name = name;
        this.department = department;
        this.birthday = birthday;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }
}
