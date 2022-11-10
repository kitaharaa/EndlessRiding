package com.kitaharaa.endlessriding.restart;

public interface ResultContract {
    interface View {
        void setScreenPreferences();
        void setScoreValue();
        void getValueFromIntent();
        void setTitleText();
    }

    interface Presenter {
        int extractIntentData();
    }
}
