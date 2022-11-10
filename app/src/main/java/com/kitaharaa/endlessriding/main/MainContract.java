package com.kitaharaa.endlessriding.main;

public interface MainContract {
    interface View {
        void createButtonListener();
    }

    interface Presenter {
        void createConfigObject();
        void fetchData();
        boolean getGamePass();
        String getWebLink();

    }
}
