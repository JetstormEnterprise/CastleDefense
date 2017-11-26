package com.enterprise.jetstorm.castledefense.gamestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by Makio on 11/18/2017.
 */

public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;

    public static final int NUMGAMESTATES = 10;
    public static final int STARTUPSCREENSTATE = 0;
    public static final int LOADINGSCREENSTATE = 1;
    public static final int STARTMENUSTATE = 2;
    public static final int MODESELECTSTATE = 3;
    public static final int OVERWORLDSTATE = 4;
    public static final int UPGRADESSTATE = 5;
    public static final int ALMANACSTATE = 6;
    public static final int GEARSETUPSTATE = 7;
    public static final int CASTLEDEFENSESTATE = 8;

    public GameStateManager() {

        gameStates = new GameState[NUMGAMESTATES];

//        currentState = STARTUPSCREENSTATE;
        currentState = CASTLEDEFENSESTATE;
        loadState(currentState);

    }

    private void loadState(int state) {

//        if (state == STARTUPSCREENSTATE)
//            gameStates[state] = new StartupScreenState(this);
//        else if (state == LOADINGSCREENSTATE)
//            gameStates[state] = new LoadingScreenState(this);
//        else if (state == STARTMENUSTATE)
//            gameStates[state] = new StartMenuState(this);
//        else if (state == MODESELECTSTATE)
//            gameStates[state] = new ModeSelectState(this);
//        else if (state == OVERWORLDSTATE)
//            gameStates[state] = new OverWorldState(this);
//        else if (state == UPGRADESSTATE)
//            gameStates[state] = new UpgradeState(this);
//        else if (state == ALMANACSTATE)
//            gameStates[state] = new AlmanacState(this);
//        else if (state == GEARSETUPSTATE)
//            gameStates[state] = new GearSetupState(this);
        if (state == CASTLEDEFENSESTATE)
            gameStates[state] = new CastleDefenseState(this);

    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void update(long fps, MotionEvent motionEvent) {

        if (gameStates[currentState] != null)
            gameStates[currentState].update(fps, motionEvent);

    }

    public void draw(Canvas canvas, Paint paint, SurfaceHolder surfaceHolder) {

        if (gameStates[currentState] != null)
            gameStates[currentState].draw(canvas, paint, surfaceHolder);
        else {
            canvas.drawColor(Color.BLACK);
        }

    }

}
