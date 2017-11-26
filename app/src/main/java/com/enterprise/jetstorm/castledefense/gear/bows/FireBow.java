package com.enterprise.jetstorm.castledefense.gear.bows;

import android.view.MotionEvent;

import com.enterprise.jetstorm.castledefense.PixelShot;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;

/**
 * Created by Makio on 11/19/2017.
 */

public class FireBow extends Bow {

    public FireBow(short x, short y) {
        super(x, y);

        damage = 60;
        attackSpeed = 70;
        shotSpeedCount = 70;
        projectileSpeed = 800;

    }

    @Override
    public void hitEnemy(Enemy enemy, PixelShot pixelShot) {

//        if (enemy.isDousedInOil) {
//            pixelShot.hit(enemy.hit(damage * 2, pixelShot.getUniqueShot(), shotType));
//        } else {
        pixelShot.hit(enemy.hit((int) calculateDamage(), pixelShot.getUniqueShot(), shotType));
//        }
        if (random.nextInt(100) <= chanceForEffect) {
//            enemy.knockback();
        }

    }

    @Override
    public void createNewShot(MotionEvent motionEvent) {
        shots.add(new PixelShot((int) motionEvent.getX(), (int) motionEvent.getY(),
                x, y, 1, projectileSpeed));
    }

}
