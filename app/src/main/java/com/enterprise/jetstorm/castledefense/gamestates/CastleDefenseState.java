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

    public CastleDefenseState(GameStateManager gsm, int screenSizeX, int screenSizeY) {
        super(gsm, screenSizeX, screenSizeY);

        random = new Random();

        enemies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            enemies.add(new ArrayList<Enemy>());
        }

        bow = new WindBow((short) (screenSizeX / 5), (short) (screenSizeY / 2), screenSizeX, screenSizeY);

        ally = new Ally(screenSizeX / 4, screenSizeY - (screenSizeY / 4), screenSizeX);

        sniper = new Sniper(screenSizeX / 4, screenSizeY - (screenSizeY / 5), screenSizeX);

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
                switch (randomLane) {

                    case 0:
                        enemies.get(0).add(new Enemy(screenSizeX, (screenSizeY / 12), (byte) 1));
                        break;

                    case 1:
                        enemies.get(1).add(new Enemy(screenSizeX, (screenSizeY / 12) * 2, (byte) 1));
                        break;

                    case 2:
                        enemies.get(2).add(new Enemy(screenSizeX, (screenSizeY / 12) * 3, (byte) 1));
                        break;

                    case 3:
                        enemies.get(3).add(new Enemy(screenSizeX, (screenSizeY / 12) * 4, (byte) 1));
                        break;

                    case 4:
                        enemies.get(4).add(new Enemy(screenSizeX, (screenSizeY / 12) * 5, (byte) 1));
                        break;

                    case 5:
                        enemies.get(5).add(new Enemy(screenSizeX, (screenSizeY / 12) * 6, (byte) 1));
                        break;

                    case 6:
                        enemies.get(6).add(new Enemy(screenSizeX, (screenSizeY / 12) * 7, (byte) 1));
                        break;

                    case 7:
                        enemies.get(7).add(new Enemy(screenSizeX, (screenSizeY / 12) * 8, (byte) 1));
                        break;

                    case 8:
                        enemies.get(8).add(new Enemy(screenSizeX, (screenSizeY / 12) * 9, (byte) 1));
                        break;

                    case 9:
                        enemies.get(9).add(new Enemy(screenSizeX, (screenSizeY / 12) * 10, (byte) 1));
                        break;

                }
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

            canvas.drawRect(0, screenSizeY / 3, screenSizeX, screenSizeY, paint);

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
