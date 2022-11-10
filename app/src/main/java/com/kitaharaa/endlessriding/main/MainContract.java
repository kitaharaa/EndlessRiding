package com.kitaharaa.endlessriding.main;

/* Contract */
public interface MainContract {
    interface View {
        void createButtonListener();
    }

    interface Presenter {
        void createConfigObject();
        void fetchData();
        boolean getGamePass();
        String getWebLink();
        void createDatabaseObject();
        void setWebLinkValue(String value);

    }
}
