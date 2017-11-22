package com.enterprise.jetstorm.castledefense.entities.enemies;

/**
 * Created by Makio on 11/17/2017.
 */

public class GoldenSlime extends Enemy {

    public GoldenSlime(int x, int y, byte preset) {
        super(x, y, preset);

        movementSpeed = 75;
        attackDamage = 5;
        goldDropped = 1000;
        health = 10;
        maxHealth = 10;

        armor = 1;
        magiceResist = 1;

        width = 75;
        health = 50;

    }

}
