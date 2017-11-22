package com.enterprise.jetstorm.castledefense.gamestates;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by Makio on 11/18/2017.
 */

public abstract class GameState {

    protected GameStateManager gsm;
    protected int screenSizeX, screenSizeY;

    public GameState(GameStateManager gsm, int screenSizeX, int screenSizeY) {
        this.gsm = gsm;
        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
    }

    public abstract void update(long fps, MotionEvent motionEvent);

    public abstract void draw(Canvas canvas, Paint paint, SurfaceHolder surfaceHolder);

}
