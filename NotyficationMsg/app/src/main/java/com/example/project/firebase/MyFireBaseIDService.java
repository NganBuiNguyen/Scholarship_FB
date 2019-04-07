package com.example.project.firebase;

import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFireBaseIDService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("global");
        final String token = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(MyFireBaseIDService.this, token,Toast.LENGTH_LONG).show();
        saveTokenToDB(token);
    }

    private void saveTokenToDB(String token) {
        new FireBaseITDask().execute(token);
    }
}
