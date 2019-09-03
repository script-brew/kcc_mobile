package com.kcc.kccm_project.store.logic;

import com.kcc.kccm_project.store.SignStore;

public class StoreFactoryLogic {
    private static StoreFactoryLogic singletonFactory;

    public StoreFactoryLogic() {
    }

    public static StoreFactoryLogic getInstance() {
        if(singletonFactory == null) {
            singletonFactory = new StoreFactoryLogic();
        }

        return singletonFactory;
    }

    public SignStore getSignStore() {
        return new SignStoreLogic();
    }
}
