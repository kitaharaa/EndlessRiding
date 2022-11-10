package com.kitaharaa.endlessriding.main;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/* Presenter */
public class MainPresenter implements MainContract.Presenter{
    MainContract.View view;
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    FirebaseDatabase database;
    String WEB_VIEW;

    /* Constructor */
    public MainPresenter(MainContract.View view) {
        this.view = view;

        createConfigObject();
        fetchData();

        createDatabaseObject();
        setWebLinkValue(WEB_VIEW);
    }


    @Override
    public void createConfigObject() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(5)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
    }

    /*Fetch data from config*/
    @Override
    public void fetchData() {
        mFirebaseRemoteConfig.fetchAndActivate();
    }

    /* Get game_pass from config*/
    @Override
    public boolean getGamePass() {
        return mFirebaseRemoteConfig.getBoolean("game_pass");
    }

    /* Get web_link from config*/
    @Override
    public String getWebLink() {
        WEB_VIEW = mFirebaseRemoteConfig.getString("web_link");
        return WEB_VIEW;
    }

    /* Create DB object*/
    @Override
    public void createDatabaseObject() {
        database = FirebaseDatabase.getInstance();
    }

    /* Pass value to DB*/
    @Override
    public void setWebLinkValue(String value) {
        DatabaseReference myRef = database.getReference("web_link");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myRef.setValue(WEB_VIEW);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
