package com.enterprise.jetstorm.castledefense.gear.bows;

import android.view.MotionEvent;

import com.enterprise.jetstorm.castledefense.PixelShot;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;

/**
 * Created by Makio on 11/19/2017.
 */

public class PoisonBow extends Bow {

    public PoisonBow(short x, short y) {
        super(x, y);

        damage = 3;
        attackSpeed = 30;
        shotSpeedCount = 30;
        projectileSpeed = 800;

    }

    @Override
    public void hitEnemy(Enemy enemy, PixelShot pixelShot) {

        pixelShot.hit(enemy.hit((int) calculateDamage(), pixelShot.getUniqueShot(), shotType));
        if (random.nextInt(100) <= chanceForEffect) {
//            enemy.poison();
        }

    }

    @Override
    public void createNewShot(MotionEvent motionEvent) {
        shots.add(new PixelShot((int) motionEvent.getX(), (int) motionEvent.getY(),
                x, y, 1, projectileSpeed));
    }

}
