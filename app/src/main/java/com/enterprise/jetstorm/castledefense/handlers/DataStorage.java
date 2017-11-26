package com.enterprise.jetstorm.castledefense.handlers;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Makio on 11/22/2017.
 */

public class DataStorage {

    public static String FILE_LOCATION;

    public static boolean doesFileExist() {

        try {

            File f = new File(FILE_LOCATION + "/CastleDefense");

            return f.exists();

        } catch (Exception e) {
            //asdfasdfasf
        }

        return false;

    }

    public static void savePlayerInformation() {

        try {

            DataOutputStream out = new DataOutputStream(
                    new FileOutputStream(FILE_LOCATION + "/CastleDefense"));

            out.writeChars(PlayerInformation.PLAYER_NAME);
            out.writeByte(PlayerInformation.PLAYER_BASIC_LEVEL);
            out.writeByte(PlayerInformation.PLAYER_ADVANCED_LEVEL);
            out.writeInt(PlayerInformation.PLAYER_BASIC_EXP);
            out.writeInt(PlayerInformation.PLAYER_ADVANCED_EXP);
            out.writeInt(PlayerInformation.PLAYER_BASIC_GOLD);
            out.writeInt(PlayerInformation.PLAYER_ADVANCED_GOLD);
            out.writeInt(PlayerInformation.PLAYER_ADVANCED_SOULS);
            out.writeByte(PlayerInformation.PLAYER_BASIC_CURRENT_STAGE);
            out.writeByte(PlayerInformation.PLAYER_ADVANCED_CURRENT_STAGE);

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_INVENTORY.length; i++) {
                out.writeShort(PlayerInformation.PLAYER_ADVANCED_INVENTORY[i]);
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_MONSTER_KILLS.length; i++) {
                out.writeShort(PlayerInformation.PLAYER_BASIC_MONSTER_KILLS[i]);
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_MONSTER_KILLS.length; i++) {
                out.writeShort(PlayerInformation.PLAYER_ADVANCED_MONSTER_KILLS[i]);
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_ACHIEVEMENTS.length; i++) {
                out.writeByte(PlayerInformation.PLAYER_BASIC_ACHIEVEMENTS[i]);
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_ACHIEVEMENTS.length; i++) {
                out.writeByte(PlayerInformation.PLAYER_ADVANCED_ACHIEVEMENTS[i]);
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_BOW_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_BASIC_BOW_UPGRADES[i].length; k++) {
                    out.writeByte(PlayerInformation.PLAYER_BASIC_BOW_UPGRADES[i][k]);
                }
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_BOW_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_ADVANCED_BOW_UPGRADES[i].length; k++) {
                    out.writeByte(PlayerInformation.PLAYER_ADVANCED_BOW_UPGRADES[i][k]);
                }
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_ALLY_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_BASIC_ALLY_UPGRADES[i].length; k++) {
                    out.writeByte(PlayerInformation.PLAYER_BASIC_ALLY_UPGRADES[i][k]);
                }
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_ALLY_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_ADVANCED_ALLY_UPGRADES[i].length; k++) {
                    out.writeByte(PlayerInformation.PLAYER_ADVANCED_ALLY_UPGRADES[i][k]);
                }
            }

            out.writeShort(PlayerInformation.PLAYER_BOW_LOCATION[0]);
            out.writeShort(PlayerInformation.PLAYER_BOW_LOCATION[1]);

            for (int i = 0; i < PlayerInformation.ALLY_LOCATIONS.length; i++) {
                out.writeShort(PlayerInformation.ALLY_LOCATIONS[i]);
            }

            out.writeShort(PlayerInformation.SCREEN_SIZE_X);
            out.writeShort(PlayerInformation.SCREEN_SIZE_Y);

            for (int i = 0; i < PlayerInformation.ENEMY_SPAWNING_LOCATIONS.length; i++) {
                out.writeShort(PlayerInformation.ENEMY_SPAWNING_LOCATIONS[i]);
            }

            for (int i = 0; i < PlayerInformation.ENEMY_WIDTH.length; i++) {
                out.writeShort(PlayerInformation.ENEMY_WIDTH[i]);
            }

            for (int i = 0; i < PlayerInformation.ENEMY_HEIGHT.length; i++) {
                out.writeShort(PlayerInformation.ENEMY_HEIGHT[i]);
            }

            out.close();

        } catch (Exception e) {
            // eafsf
        }

    }

    public static void loadPlayerInformation() {

        try {

            DataInputStream in = new DataInputStream(
                    new FileInputStream(FILE_LOCATION + "/CastleDefense"));

            char[] tempCharList = new char[20];

            for (int i = 0; i < 20; i++) {
                tempCharList[i] = in.readChar();
            }

            PlayerInformation.PLAYER_NAME = new String(tempCharList);
            PlayerInformation.PLAYER_BASIC_LEVEL = in.readByte();
            PlayerInformation.PLAYER_ADVANCED_LEVEL = in.readByte();
            PlayerInformation.PLAYER_BASIC_EXP = in.readInt();
            PlayerInformation.PLAYER_ADVANCED_EXP = in.readInt();
            PlayerInformation.PLAYER_BASIC_GOLD = in.readInt();
            PlayerInformation.PLAYER_ADVANCED_GOLD = in.readInt();
            PlayerInformation.PLAYER_ADVANCED_SOULS = in.readInt();
            PlayerInformation.PLAYER_BASIC_CURRENT_STAGE = in.readByte();
            PlayerInformation.PLAYER_ADVANCED_CURRENT_STAGE = in.readByte();

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_INVENTORY.length; i++) {
                PlayerInformation.PLAYER_ADVANCED_INVENTORY[i] = in.readShort();
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_MONSTER_KILLS.length; i++) {
                PlayerInformation.PLAYER_BASIC_MONSTER_KILLS[i] = in.readShort();
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_MONSTER_KILLS.length; i++) {
                PlayerInformation.PLAYER_ADVANCED_MONSTER_KILLS[i] = in.readShort();
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_ACHIEVEMENTS.length; i++) {
                PlayerInformation.PLAYER_BASIC_ACHIEVEMENTS[i] = in.readByte();
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_ACHIEVEMENTS.length; i++) {
                PlayerInformation.PLAYER_ADVANCED_ACHIEVEMENTS[i] = in.readByte();
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_BOW_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_BASIC_BOW_UPGRADES[i].length; k++) {
                    PlayerInformation.PLAYER_BASIC_BOW_UPGRADES[i][k] = in.readByte();
                }
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_BOW_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_ADVANCED_BOW_UPGRADES[i].length; k++) {
                    PlayerInformation.PLAYER_ADVANCED_BOW_UPGRADES[i][k] = in.readByte();
                }
            }

            for (int i = 0; i < PlayerInformation.PLAYER_BASIC_ALLY_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_BASIC_ALLY_UPGRADES[i].length; k++) {
                    PlayerInformation.PLAYER_BASIC_ALLY_UPGRADES[i][k] = in.readByte();
                }
            }

            for (int i = 0; i < PlayerInformation.PLAYER_ADVANCED_ALLY_UPGRADES.length; i++) {
                for (int k = 0; k < PlayerInformation.PLAYER_ADVANCED_ALLY_UPGRADES[i].length; k++) {
                    PlayerInformation.PLAYER_ADVANCED_ALLY_UPGRADES[i][k] = in.readByte();
                }
            }

            PlayerInformation.PLAYER_BOW_LOCATION[0] = in.readShort();
            PlayerInformation.PLAYER_BOW_LOCATION[1] = in.readShort();

            for (int i = 0; i < PlayerInformation.ALLY_LOCATIONS.length; i++) {
                PlayerInformation.ALLY_LOCATIONS[i] = in.readShort();
            }

            PlayerInformation.SCREEN_SIZE_X = in.readShort();
            PlayerInformation.SCREEN_SIZE_Y = in.readShort();

            for (int i = 0; i < PlayerInformation.ENEMY_SPAWNING_LOCATIONS.length; i++) {
                PlayerInformation.ENEMY_SPAWNING_LOCATIONS[i] = in.readShort();
            }

            for (int i = 0; i < PlayerInformation.ENEMY_WIDTH.length; i++) {
                PlayerInformation.ENEMY_WIDTH[i] = in.readShort();
            }

            for (int i = 0; i < PlayerInformation.ENEMY_HEIGHT.length; i++) {
                PlayerInformation.ENEMY_HEIGHT[i] = in.readShort();
            }

            in.close();

        } catch (Exception e) {
            // wefw
        }

    }

}
