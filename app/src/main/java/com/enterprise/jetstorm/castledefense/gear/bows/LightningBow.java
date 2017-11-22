package com.enterprise.jetstorm.castledefense.gear.bows;

import android.view.MotionEvent;

import com.enterprise.jetstorm.castledefense.PixelShot;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;

/**
 * Created by Makio on 11/19/2017.
 */

public class LightningBow extends Bow {

    public LightningBow(short x, short y, int screenX, int screenY) {
        super(x, y, screenX, screenY);

        damage = 5;
        attackSpeed = 15;
        shotSpeedCount = 25;
        projectileSpeed = 800;

    }

    @Override
    public void hitEnemy(Enemy enemy, PixelShot pixelShot) {

//        if (enemy.isDousedInWater) {
//            pixelShot.hit(enemy.hit(damage * 2, pixelShot.getUniqueShot(), shotType));
//        } else {
            pixelShot.hit(enemy.hit((int) calculateDamage(), pixelShot.getUniqueShot(), shotType));
//        }
        if (random.nextInt(100) <= chanceForEffect) {
            // bounce shot
        }

    }

    @Override
    public void createNewShot(MotionEvent motionEvent) {
        shots.add(new PixelShot((int) motionEvent.getX(), (int) motionEvent.getY(),
                x, y, 1, projectileSpeed));
    }

}
