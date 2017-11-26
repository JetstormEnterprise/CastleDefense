package com.enterprise.jetstorm.castledefense.handlers;

import android.view.Display;

/**
 * Created by Makio on 11/22/2017.
 */

public class PlayerInformation {

    public static String PLAYER_NAME = "Ancientjetstorm     ";
    public static byte PLAYER_BASIC_LEVEL = 1, PLAYER_ADVANCED_LEVEL = 1;
    public static int PLAYER_BASIC_EXP = 0, PLAYER_ADVANCED_EXP = 0;
    public static int PLAYER_BASIC_GOLD = 0, PLAYER_ADVANCED_GOLD = 0;
    public static int PLAYER_ADVANCED_SOULS = 0;
    public static byte PLAYER_BASIC_CURRENT_STAGE = 1, PLAYER_ADVANCED_CURRENT_STAGE = 1;

    public static short[] PLAYER_ADVANCED_INVENTORY = new short[50];
    public static short[] PLAYER_BASIC_MONSTER_KILLS = new short[20];
    public static short[] PLAYER_ADVANCED_MONSTER_KILLS = new short[20];

    public static byte[] PLAYER_BASIC_ACHIEVEMENTS = new byte[30];
    public static byte[] PLAYER_ADVANCED_ACHIEVEMENTS = new byte[30];

    public static byte[][] PLAYER_BASIC_BOW_UPGRADES = new byte[2][3];
    public static byte[][] PLAYER_ADVANCED_BOW_UPGRADES = new byte[10][3];

    public static byte[][] PLAYER_BASIC_ALLY_UPGRADES = new byte[10][3];
    public static byte[][] PLAYER_ADVANCED_ALLY_UPGRADES = new byte[10][3];

    public static short[] PLAYER_BOW_LOCATION = new short[2];
    public static short[] ALLY_LOCATIONS = new short[4];


    /**
     *   NON PLAYER INFO
     */

    public static short SCREEN_SIZE_X = 0, SCREEN_SIZE_Y = 0;

    public static short[] ENEMY_SPAWNING_LOCATIONS = new short[10];

    public static short[] ENEMY_WIDTH = new short[20];
    public static short[] ENEMY_HEIGHT = new short[20];



    public static void createSpawnLocations() {

        PLAYER_BOW_LOCATION[0] = (short) (SCREEN_SIZE_X / 6);
        PLAYER_BOW_LOCATION[1] = (short) (SCREEN_SIZE_Y / 2);

        ALLY_LOCATIONS[0] = (short) (SCREEN_SIZE_X / 5);
        ALLY_LOCATIONS[1] = (short) (SCREEN_SIZE_Y - (SCREEN_SIZE_Y / 3));
        ALLY_LOCATIONS[2] = (short) (SCREEN_SIZE_X / 5);
        ALLY_LOCATIONS[3] = (short) (SCREEN_SIZE_Y - (SCREEN_SIZE_Y / 5));

        for (int i = 0; i < ENEMY_SPAWNING_LOCATIONS.length; i++) {
            ENEMY_SPAWNING_LOCATIONS[i] = (short) ((SCREEN_SIZE_Y / 12) * (i + 1));
        }

    }



}
