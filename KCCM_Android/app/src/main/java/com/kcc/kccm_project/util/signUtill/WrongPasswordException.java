package com.kcc.kccm_project.util.signUtill;

public class WrongPasswordException extends RuntimeException
{

    private static final long serialVersionUID = -6658367528597433288L;

    public WrongPasswordException(String message)
    {
        super(message);
    }

}
