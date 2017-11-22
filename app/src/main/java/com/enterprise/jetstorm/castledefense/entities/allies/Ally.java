package com.enterprise.jetstorm.castledefense.entities.allies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;
import com.enterprise.jetstorm.castledefense.PixelShot;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Makio on 11/18/2017.
 */

public class Ally {

    short shotCount;

    short damage, attackSpeed, attackRange, attackVelocity;
    short width, height;
    short closestEnemyX, closestEnemyY, enemySpeed;

    boolean shooting;

//    Bitmap sprite;

    RectF hitbox;

    Random random;

    short red, green, blue;

    ArrayList<PixelShot> shots = new ArrayList<>();

    public Ally(int x, int y, int screenX) {

        height = 100;
        width = 50;

        hitbox = new RectF();
        hitbox.left = x;
        hitbox.right = x + width;
        hitbox.top = y;
        hitbox.bottom = y + height;

        random = new Random();

        red = (short) random.nextInt(255);
        green = (short) random.nextInt(255);
        blue = (short) random.nextInt(255);

        damage = 5;
        attackSpeed = 50;
        attackRange = (short) (screenX - (screenX / 5));
        attackVelocity = 500;
        shotCount = (byte) attackSpeed;

    }

//    public void update(long fps, ArrayList<Enemy> enemies) {
//
//        for (int i = 0; i < enemies.size(); i++) {
//            short tempEnemyX = (short) enemies.get(i).getHitbox().left;
//            if (tempEnemyX < attackRange) {
//                shooting = true;
//                if (tempEnemyX < closestEnemyX) {
//                    closestEnemyX = tempEnemyX;
//                    closestEnemyY = (short) enemies.get(i).getHitbox().top;
//                } // If
//            } // If
//
//            for (int k = 0; k < shots.size(); k++) {
//                if (RectF.intersects(enemies.get(i).getHitbox(), shots.get(k).getRect())) {
//                    shots.get(k).hit(enemies.get(i).hit(damage, shots.get(k).getUniqueShot()));
//                    if (shots.get(k).getHealth() <= 0) {
//                        shots.remove(k);
//                        k--;
//                    }
//                } // If
//            } // For
//
//            if (enemies.get(i).isDead()) {
//                enemies.remove(i);
//                i--;
//            }
//        } // For
//
//        if (shooting) {
//            if (shotCount == attackSpeed) {
//                if (closestEnemyY >= hitbox.top - 50) {
//                    closestEnemyY = (short) (closestEnemyY + 50);
//                }
//                shots.add(new PixelShot(closestEnemyX, closestEnemyY,
//                        hitbox.left + (width / 2), hitbox.top + (height / 2),
//                        1, attackVelocity));
//                shotCount = 0;
//            }
//        }
//
//        if (shotCount < attackSpeed) {
//            shotCount++;
//        }
//
//        for (int i = 0; i < shots.size(); i++) {
//            shots.get(i).update(fps);
//        }
//
//        shooting = false;
//        closestEnemyX = 2000;
//
//    }

    public void update(long fps, ArrayList<ArrayList<Enemy>> enemies) {

        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < enemies.get(i).size(); j++) {
                short tempEnemyX = (short) enemies.get(i).get(j).getHitbox().left;
                if (tempEnemyX < attackRange) {
                    shooting = true;
                    if (tempEnemyX < closestEnemyX) {
                        closestEnemyX = tempEnemyX;
                        closestEnemyY = (short) enemies.get(i).get(j).getHitbox().top;
                    } // If
                } // If

                for (int k = 0; k < shots.size(); k++) {
                    if (RectF.intersects(enemies.get(i).get(j).getHitbox(), shots.get(k).getRect())) {
                        shots.get(k).hit(enemies.get(i).get(j).hit(damage, shots.get(k).getUniqueShot(), (byte) 0));
                        if (shots.get(k).getHealth() <= 0) {
                            shots.remove(k);
                            k--;
                        }
                    } // If
                } // For k

                if (enemies.get(i).get(j).isDead()) {
                    enemies.get(i).remove(j);
                    j--;
                }
            } // for j
        } // For i

        if (shooting) {
            if (shotCount == attackSpeed) {
                if (closestEnemyY >= hitbox.top - 50) {
                    closestEnemyY = (short) (closestEnemyY + 50);
                }
                shots.add(new PixelShot(closestEnemyX, closestEnemyY,
                        hitbox.left + (width / 2), hitbox.top + (height / 2),
                        1, attackVelocity));
                shotCount = 0;
            }
        }

        if (shotCount < attackSpeed) {
            shotCount++;
        }

        for (int i = 0; i < shots.size(); i++) {
            shots.get(i).update(fps);
        }

        shooting = false;
        closestEnemyX = 2000;

    }

    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.RED);

        for (int i = 0; i < shots.size(); i++) {
            canvas.drawRect(shots.get(i).getRect(), paint);
        }

        paint.setColor(Color.rgb(red, green, blue));

        canvas.drawRect(hitbox, paint);



    }

}
