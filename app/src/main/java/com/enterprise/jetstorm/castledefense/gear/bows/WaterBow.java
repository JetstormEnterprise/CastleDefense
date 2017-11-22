package com.enterprise.jetstorm.castledefense.gear.bows;

import android.view.MotionEvent;

import com.enterprise.jetstorm.castledefense.PixelShot;
import com.enterprise.jetstorm.castledefense.entities.enemies.Enemy;

/**
 * Created by Makio on 11/19/2017.
 */

public class WaterBow extends Bow{

    public WaterBow(short x, short y, int screenX, int screenY) {
        super(x, y, screenX, screenY);

        damage = 10;
        attackSpeed = 30;
        shotSpeedCount = 30;
        projectileSpeed = 800;
        chanceToCrit = 20;

    }

    @Override
    public void hitEnemy(Enemy enemy, PixelShot pixelShot) {

        pixelShot.hit(enemy.hit((int)calculateDamage(), pixelShot.getUniqueShot(), shotType));
        if (random.nextInt(100) <= chanceForEffect) {
//            enemy.douseInWater();
        }

    }

    @Override
    public void createNewShot(MotionEvent motionEvent) {
        shots.add(new PixelShot((int) motionEvent.getX(), (int) motionEvent.getY(),
                x, y, 1, projectileSpeed));
    }

}
