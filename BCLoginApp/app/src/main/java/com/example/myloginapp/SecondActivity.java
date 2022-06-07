package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondaryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null){

            String personName = acct.getDisplayName();
            Log.w(TAG, "Last signed-in account, personName->" + personName);
            Log.w(TAG, "Last signed-in account, getIdToken->" + acct.getIdToken());

            String idToken = acct.getIdToken();

            String androidID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            System.out.println("Android Id " + androidID);

            Log.w(TAG, "androidID ->" + androidID);

            MyAsyncTasks myAsyncTasks = new MyAsyncTasks(idToken, androidID);
            myAsyncTasks.execute();

        } else {
            Log.w(TAG, "NO ONE Has logged in ");
        }
    }
}