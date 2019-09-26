package com.kcc.kccm_project.service.logic;

import com.kcc.kccm_project.service.ServiceFactory;
import com.kcc.kccm_project.service.SignService;

public class ServiceFactoryLogic implements ServiceFactory
{
    private static ServiceFactoryLogic singletonFactory;

    public ServiceFactoryLogic() { }

    public static ServiceFactoryLogic getInstance()
    {
        if ( singletonFactory == null )
            singletonFactory = new ServiceFactoryLogic();

        return singletonFactory;
    }

    @Override
    public SignService createSignService()
    {
        return new SignServiceLogic();
    }
}
