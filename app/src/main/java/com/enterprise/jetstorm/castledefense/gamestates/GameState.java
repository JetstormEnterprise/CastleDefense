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

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update(long fps, MotionEvent motionEvent);

    public abstract void draw(Canvas canvas, Paint paint, SurfaceHolder surfaceHolder);

}
