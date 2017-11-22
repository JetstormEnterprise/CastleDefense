package com.enterprise.jetstorm.castledefense.entities.enemies;

/**
 * Created by Makio on 11/17/2017.
 */

public class GreenSlime extends Enemy {

    public GreenSlime(int x, int y, byte preset) {
        super(x, y, preset);

        movementSpeed = 75;
        attackDamage = 5;
        goldDropped = 1;
        health = 10;
        maxHealth = 10;

        armor = 1;

        width = 75;
        health = 50;

    }
}
