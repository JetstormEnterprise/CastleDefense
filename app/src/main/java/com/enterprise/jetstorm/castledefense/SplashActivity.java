package com.enterprise.jetstorm.castledefense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.enterprise.jetstorm.castledefense.handlers.DataStorage;

/**
 * Created by Makio on 11/22/2017.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataStorage.FILE_LOCATION = getFilesDir().getAbsolutePath();

        Log.d("Info", "Exist? " + DataStorage.doesFileExist());

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
        finish();
    }
}