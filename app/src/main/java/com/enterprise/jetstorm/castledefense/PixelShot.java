package com.enterprise.jetstorm.castledefense;

import android.graphics.RectF;
import android.util.Log;
import android.view.VelocityTracker;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Makio on 11/8/2017.
 */

public class PixelShot {

    RectF rect;
    Random random;
    float xVelocity, yVelocity;
    byte health;
    final byte ballWidth = 10, ballHeight = 10;

    short uniqueShot;

    float trajectoryAngle, projectileSpeed = 1000;

    public PixelShot(int x, int y, int xHalf, int yHalf) {

        rect = new RectF();

        int xStart = xHalf / 4;
        int yStart = yHalf / 2;

        setPosition(xStart, yStart);
        setVelocity(x, y, xStart, yStart);

    }

    public PixelShot(int x, int y, float startX, float startY, int health, float projectileSpeed) {

        rect = new RectF();
        random = new Random();

        this.health = (byte) health;
        uniqueShot = (short) random.nextInt(30000);
        this.projectileSpeed = projectileSpeed;

        setPosition(startX, startY);
        setVelocity(x, y, (int)startX, (int)startY);

    }

    public RectF getRect() { return rect; }

    public byte getHealth() { return health; }

    public short getUniqueShot() { return uniqueShot; }

    public void hit(boolean hitBefore) {
        if (!hitBefore) {
            health -= 1;
        }
    }

    public void update(long fps) {

        setPosition((rect.left + xVelocity * projectileSpeed / fps), (rect.top + yVelocity * projectileSpeed / fps));

    }

    public void setVelocity(int x, int y, int xStart, int yStart) {

        trajectoryAngle = (float) Math.atan2((x - xStart), (y - yStart));

        xVelocity = (float)(Math.sin(trajectoryAngle));
        yVelocity = (float)(Math.cos(trajectoryAngle));

    }

    public void setPosition(float x, float y) {

        rect.left = x;
        rect.right = x + ballWidth;
        rect.top = y;
        rect.bottom = y + ballHeight;

    }

}
