package com.enterprise.jetstorm.castledefense.entities.allies;

/**
 * Created by Makio on 11/18/2017.
 */

public class Sniper extends Ally {

    public Sniper(int x, int y, int screenX) {
        super(x, y, screenX);

        damage = 50;
        attackSpeed = 100;
        attackVelocity = 1500;
        attackRange = (short) (screenX - (screenX / 10));

    }

}
