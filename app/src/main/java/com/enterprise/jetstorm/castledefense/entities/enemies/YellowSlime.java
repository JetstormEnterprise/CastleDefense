package com.enterprise.jetstorm.castledefense.entities.enemies;

/**
 * Created by Makio on 11/17/2017.
 */

public class YellowSlime extends Enemy {

    public YellowSlime(int x, int y, byte preset) {
        super(x, y, preset);

        movementSpeed = 75;
        attackDamage = 5;
        goldDropped = 1;
        health = 10;
        maxHealth = 10;

        magiceResist = 1;

        width = 75;
        health = 50;

    }
}
