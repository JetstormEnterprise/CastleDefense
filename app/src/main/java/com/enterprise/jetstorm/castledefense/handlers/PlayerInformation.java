package com.enterprise.jetstorm.castledefense.handlers;

/**
 * Created by Makio on 11/22/2017.
 */

public class PlayerInformation {

    static String PLAYER_NAME;
    static byte PLAYER_BASIC_LEVEL, PLAYER_ADVANCED_LEVEL;
    static int PLAYER_BASIC_EXP, PLAYER_ADVANCED_EXP;
    static int PLAYER_BASIC_GOLD, PLAYER_ADVANCED_GOLD;
    static int PLAYER_ADVANCED_SOULS;
    static byte PLAYER_BASIC_CURRENT_STAGE, PLAYER_ADVANCED_CURRENT_STAGE;

    static short[] PLAYER_ADVANCED_INVENTORY = new short[50];
    static short[] PLAYER_BASIC_MONSTER_KILLS = new short[20];
    static short[] PLAYER_ADVANCED_MONSTER_KILLS = new short[20];

    static byte[] PLAYER_BASIC_ACHIEVEMENTS = new byte[30];
    static byte[] PLAYER_ADVANCED_ACHIEVEMENTS = new byte[30];

    static byte[][] PLAYER_BASIC_BOW_UPGRADES = new byte[2][3];
    static byte[][] PLAYER_ADVANCED_BOW_UPGRADES = new byte[10][3];

    static byte[][] PLAYER_BASIC_ALLY_UPGRADES = new byte[10][3];
    static byte[][] PLAYER_ADVANCED_ALLY_UPGRADES = new byte[10][3];

}
