package com.enterprise.jetstorm.castledefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.enterprise.jetstorm.castledefense.gamestates.GameStateManager;

/**
 * Created by Makio on 11/18/2017.
 */

public class Window extends SurfaceView implements Runnable {

    Thread gameThread = null;
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Paint paint;
    MotionEvent motionEvent;

    GameStateManager gsm;

    volatile boolean playing;
    boolean paused = true;

    long fps;


    public Window(Context context) {

        super(context);

        surfaceHolder = getHolder();
        paint = new Paint();

        gsm = new GameStateManager();

        gameThread = new Thread(this);
        gameThread.start();


    }

    @Override
    public void run() {

        while (playing) {

            long startFrameTime = System.currentTimeMillis();

            if (!paused) {
                update();
            }

            draw();

            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }

        }

    }

    private void update() {

        gsm.update(fps, motionEvent);

    }

    private void draw() {

        gsm.draw(canvas, paint, surfaceHolder);

    }

    public void pause() {

        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "joining thread");
        }

    }

    public void resume() {

        playing = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        this.motionEvent = motionEvent;

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:

                paused = false;

                break;

        }

        return true;

    }

}
