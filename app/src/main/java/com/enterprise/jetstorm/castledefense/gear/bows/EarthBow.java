package com.enterprise.jetstorm.castledefense.gear.bows;

import android.view.MotionEvent;

import com.enterprise.jetstorm.castledefense.PixelShot;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;

/**
 * Created by Makio on 11/19/2017.
 */

public class EarthBow extends Bow {

    public EarthBow(short x, short y, int screenX, int screenY) {
        super(x, y, screenX, screenY);

        damage = 30;
        attackSpeed = 60;
        shotSpeedCount = 60;
        projectileSpeed = 800;

    }

    @Override
    public void hitEnemy(Enemy enemy, PixelShot pixelShot) {

        pixelShot.hit(enemy.hit((int) calculateDamage(), pixelShot.getUniqueShot(), shotType));
        if (random.nextInt(100) <= chanceForEffect) {
//            explode
        }

    }

    @Override
    public void createNewShot(MotionEvent motionEvent) {
        shots.add(new PixelShot((int) motionEvent.getX(), (int) motionEvent.getY(),
                x, y, 1, projectileSpeed));
    }

}
