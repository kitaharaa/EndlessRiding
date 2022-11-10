package com.kitaharaa.endlessriding.result;

/* Contract */
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
