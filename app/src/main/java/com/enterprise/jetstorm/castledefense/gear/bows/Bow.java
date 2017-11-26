package com.enterprise.jetstorm.castledefense.gear.bows;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.enterprise.jetstorm.castledefense.PixelShot;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;
import com.enterprise.jetstorm.castledefense.handlers.PlayerInformation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Makio on 11/16/2017.
 */

public class Bow {

    short damage;
    short attackSpeed;
    short projectileSpeed;
    short shotSpeedCount;
    short x, y;

    int screenX, screenY;

    byte shotType;
    byte chanceToCrit, chanceForEffect;

    double critMultiplier;

    boolean firing;

    Random random;

    ArrayList<PixelShot> shots;

    public Bow(short x, short y) {

        shots = new ArrayList<>();

        random = new Random();

        shotType = 0;
        damage = 5;
        attackSpeed = 50;
        shotSpeedCount = 50;
        projectileSpeed = 700;

        chanceForEffect = 10;
        chanceToCrit = 5;
        critMultiplier = 1.5;

        this.x = x;
        this.y = y;

        screenX = PlayerInformation.SCREEN_SIZE_X;
        screenY = PlayerInformation.SCREEN_SIZE_Y;

    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    protected double calculateDamage() {
        if (random.nextInt(100) <= chanceToCrit) {
            return damage * critMultiplier;
        } else {
            return damage;
        }
    }

    public void hitEnemy(Enemy enemy, PixelShot pixelShot) {
        pixelShot.hit(enemy.hit((int)calculateDamage(), pixelShot.getUniqueShot(), shotType));
    }

    public void createNewShot(MotionEvent motionEvent) {

        shots.add(new PixelShot((int) motionEvent.getX(), (int) motionEvent.getY(), x, y,
                2, projectileSpeed));

    }

//    public void update(long fps, ArrayList<Enemy> enemies, MotionEvent motionEvent) {
//
//        if (firing && shotSpeedCount >= attackSpeed) {
//            createNewShot(motionEvent);
//            shotSpeedCount = 0;
//        }
//
//        if (shotSpeedCount < attackSpeed) {
//            shotSpeedCount++;
//        }
//
//        for (int i = 0; i < enemies.size(); i++) {
//            for (int k = 0; k < shots.size(); k++) {
//                if (RectF.intersects(enemies.get(i).getHitbox(), shots.get(k).getRect())) {
//                    hitEnemy(enemies.get(i), shots.get(k));
//                }
//                if (shots.get(k).getHealth() == 0) {
//                    shots.remove(k);
//                }
//            }
//
//        }
//
//        for (int i = 0; i < shots.size(); i++) {
//            shots.get(i).update(fps);
//            if (shots.get(i).getRect().left > screenX
//                    || shots.get(i).getRect().right < 0
//                    || shots.get(i).getRect().top > screenY
//                    || shots.get(i).getRect().bottom < 0) {
//                shots.remove(i);
//                i--;
//            }
//        }
//
//    }

    public void update(long fps, ArrayList<ArrayList<Enemy>> enemies, MotionEvent motionEvent) {

        if (firing && shotSpeedCount >= attackSpeed) {
            createNewShot(motionEvent);
            shotSpeedCount = 0;
        }

        if (shotSpeedCount < attackSpeed) {
            shotSpeedCount++;
        }

        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < enemies.get(i).size(); j++) {
                for (int k = 0; k < shots.size(); k++) {
                    if (RectF.intersects(enemies.get(i).get(j).getHitbox(), shots.get(k).getRect())) {
                        hitEnemy(enemies.get(i).get(j), shots.get(k));
                    }
                    if (shots.get(k).getHealth() == 0) {
                        shots.remove(k);
                    }
                } // for k
            } // for j

        } // for i

        for (int i = 0; i < shots.size(); i++) {
            shots.get(i).update(fps);
            if (shots.get(i).getRect().left > screenX
                    || shots.get(i).getRect().right < 0
                    || shots.get(i).getRect().top > screenY
                    || shots.get(i).getRect().bottom < 0) {
                shots.remove(i);
                i--;
            }
        }

    }

    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.BLUE);

        for (int i = 0; i < shots.size(); i++) {
            canvas.drawRect(shots.get(i).getRect(), paint);
        }

    }

}
