package com.enterprise.jetstorm.castledefense.gamestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.enterprise.jetstorm.castledefense.gear.bows.WindBow;
import com.enterprise.jetstorm.castledefense.entities.allies.Ally;
import com.enterprise.jetstorm.castledefense.entities.allies.Sniper;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;
import com.enterprise.jetstorm.castledefense.handlers.DataStorage;
import com.enterprise.jetstorm.castledefense.handlers.PlayerInformation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Makio on 11/18/2017.
 */

public class CastleDefenseState extends GameState {

    ArrayList<ArrayList<Enemy>> enemies;

    Ally ally;
    Ally sniper;

    WindBow bow;

    Random random;

    short tempEnemyCount;
    long fpsCheck;

    boolean spawning = true;

    public CastleDefenseState(GameStateManager gsm) {
        super(gsm);

        DataStorage.doesFileExist();

        PlayerInformation.createSpawnLocations();

        DataStorage.savePlayerInformation();

        DataStorage.loadPlayerInformation();

        random = new Random();

        enemies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            enemies.add(new ArrayList<Enemy>());
        }

        bow = new WindBow(PlayerInformation.PLAYER_BOW_LOCATION[0],
                PlayerInformation.PLAYER_BOW_LOCATION[1], PlayerInformation.SCREEN_SIZE_X,
                PlayerInformation.SCREEN_SIZE_Y);

        ally = new Ally(PlayerInformation.ALLY_LOCATIONS[0], PlayerInformation.ALLY_LOCATIONS[1],
                PlayerInformation.SCREEN_SIZE_X, PlayerInformation.SCREEN_SIZE_Y);

        sniper = new Sniper(PlayerInformation.ALLY_LOCATIONS[2], PlayerInformation.ALLY_LOCATIONS[3],
                PlayerInformation.SCREEN_SIZE_X, PlayerInformation.SCREEN_SIZE_Y);

        tempEnemyCount = 0;

    }

    public void update(long fps, MotionEvent motionEvent) {

        fpsCheck = fps;

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            bow.setFiring(true);
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            bow.setFiring(false);
        }

        if (spawning) {
            if (tempEnemyCount == 50) {
                byte randomLane = (byte) random.nextInt(10);
                enemies.get(randomLane).add(new Enemy(PlayerInformation.SCREEN_SIZE_X,
                        PlayerInformation.ENEMY_SPAWNING_LOCATIONS[randomLane], (byte) 1));
//                enemies.add(new Enemy(screenSizeX, screenSizeY, (byte) 0));
                tempEnemyCount = 0;
            } else {
                tempEnemyCount++;
            }
        }

//        for (int i = 0; i < enemies.size(); i++) {
//            enemies.get(i).update(fps);
//            if (enemies.get(i).isDead()) {
//                enemies.remove(i);
//                i--;
//            }
//        }

        for (int i = 0; i < enemies.size(); i++) {
            for (int k = 0; k < enemies.get(i).size(); k++) {
                enemies.get(i).get(k).update(fps);
                if (enemies.get(i).get(k).isDead()) {
                    enemies.get(i).remove(k);
                    k--;
                }
            }
        }

        bow.update(fps, enemies, motionEvent);
        ally.update(fps, enemies);
        sniper.update(fps, enemies);

    }

    public void draw(Canvas canvas, Paint paint, SurfaceHolder surfaceHolder) {

        if (surfaceHolder.getSurface().isValid()) {

            canvas = surfaceHolder.lockCanvas();

            canvas.drawColor(Color.rgb(100, 150, 255));

            paint.setColor(Color.rgb(244, 220, 66));

            canvas.drawRect(0, PlayerInformation.SCREEN_SIZE_Y / 3,
                    PlayerInformation.SCREEN_SIZE_X, PlayerInformation.SCREEN_SIZE_Y, paint);

            paint.setColor(Color.BLUE);

            bow.draw(canvas, paint);
            ally.draw(canvas, paint);
            sniper.draw(canvas, paint);

            paint.setColor(Color.RED);
//            for (int i = 0; i < enemies.size(); i++) {
//                enemies.get(i).draw(canvas, paint);
//            }
            for (int i = 0; i < enemies.size(); i++) {
                for (int k = 0; k < enemies.get(i).size(); k++) {
                    enemies.get(i).get(k).draw(canvas, paint);
                }
            }

            paint.setTextSize(100);

            canvas.drawText("" + fpsCheck, 50, 50, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);

        }

    }

}
