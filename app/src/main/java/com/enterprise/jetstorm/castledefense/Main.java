package com.enterprise.jetstorm.castledefense;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.enterprise.jetstorm.castledefense.handlers.DataStorage;
import com.enterprise.jetstorm.castledefense.handlers.PlayerInformation;

public class Main extends Activity {

    Window gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        DataStorage.FILE_LOCATION = getFilesDir().getAbsolutePath();
        PlayerInformation.SCREEN_SIZE_X = (short) size.x;
        PlayerInformation.SCREEN_SIZE_Y = (short) size.y;

        // Initialize
        gameView = new Window(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        gameView.pause();
    }

}
