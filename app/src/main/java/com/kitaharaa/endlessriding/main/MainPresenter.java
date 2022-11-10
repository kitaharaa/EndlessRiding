package com.kitaharaa.endlessriding.main;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainPresenter implements MainContract.Presenter{
    MainContract.View view;
    FirebaseRemoteConfig mFirebaseRemoteConfig;

    public MainPresenter(MainContract.View view) {
        this.view = view;

        createConfigObject();
        fetchData();
    }

    @Override
    public void createConfigObject() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(5)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
    }

    @Override
    public void fetchData() {
        mFirebaseRemoteConfig.fetchAndActivate();

    }

    @Override
    public boolean getGamePass() {
        return mFirebaseRemoteConfig.getBoolean("game_pass");
    }

    @Override
    public String getWebLink() {
        return mFirebaseRemoteConfig.getString("web_link");
    }
}
