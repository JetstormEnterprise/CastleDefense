package com.enterprise.jetstorm.castledefense.gear.bows;

import android.view.MotionEvent;

import com.enterprise.jetstorm.castledefense.PixelShot;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;

/**
 * Created by Makio on 11/19/2017.
 */

public class IceBow extends Bow {

    public IceBow(short x, short y) {
        super(x, y);

        damage = 10;
        attackSpeed = 30;
        shotSpeedCount = 30;
        projectileSpeed = 800;

    }

    @Override
    public void hitEnemy(Enemy enemy, PixelShot pixelShot) {

        pixelShot.hit(enemy.hit((int) calculateDamage(), pixelShot.getUniqueShot(), shotType));
//        if (enemy.isdousedInWater() || random.nextInt(100) <= chanceForEffect) {
//            enemy.freeze();
//        }

    }

    @Override
    public void createNewShot(MotionEvent motionEvent) {
        shots.add(new PixelShot((int) motionEvent.getX(), (int) motionEvent.getY(),
                x, y, 1, projectileSpeed));
    }

}
