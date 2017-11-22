package com.enterprise.jetstorm.castledefense;

/**
 * Created by Makio on 11/13/2017.
 */

public class Castle {

    short defense, mana;

    int health;

    boolean[] upgrades;

    public void Castle() {

    }

    public void Caste(short defenseB, short manaB, int healthB, boolean[] upgradesB) {

        defense = defenseB;
        mana = manaB;
        health = healthB;
        upgrades = upgradesB;

    }

}
