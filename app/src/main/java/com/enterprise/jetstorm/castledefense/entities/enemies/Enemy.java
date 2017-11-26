package com.enterprise.jetstorm.castledefense.entities.enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.enterprise.jetstorm.castledefense.handlers.PlayerInformation;

/**
 * Created by Makio on 11/12/2017.
 */

public class Enemy {

    byte attackRange, attackSpeed;
    byte armor, magiceResist;

    short xPosition, yPosition;
    short width, height;
    short attackDamage;
    short goldDropped;

    short movementSpeed;
    short spotToStop;

    byte lastHitTracker;
    short[] lastHitID;

    float health, maxHealth;
    float percentHealth;

    boolean dead;
    boolean flying;
    boolean boss;
    boolean dousedInWater, dousedInOil;
    boolean poisoned, bleeding;
    boolean slowed, frozen;

    Bitmap enemyImage;

    RectF hitbox;

    public Enemy(int x, int y, byte preset) {

        movementSpeed = 100;
        attackRange = 0;
        attackSpeed = 1;
        armor = 0;
        magiceResist = 0;

        lastHitTracker = 0;
        lastHitID = new short[3];
        for (int i = 0; i < lastHitID.length; i++) {
            lastHitID[i] = 0;
        }

        spotToStop = (short) (x / 3);

        attackDamage = 1;
        goldDropped = 1;
        health = 34;
        maxHealth = 34;
        percentHealth = 1;

        height = (short) (PlayerInformation.SCREEN_SIZE_Y / 20);
        width = (short) (PlayerInformation.SCREEN_SIZE_X / 40);

        xPosition = (short) x;
        yPosition = createYPosition(y, preset);

        hitbox = new RectF(x, yPosition, x + width, yPosition + height);

    }

    public short createYPosition(int y, byte preset) {

        switch (preset) {

            case 0:

                short tempY = (short) (y / 2);
                if (!flying) {
                    return (short) (((Math.random() * tempY) + tempY) - (height * 1.5));
                } else {
                    return (short) (Math.random() * tempY);
                }

            case 1:

                return (short) y;

        }

        return (short) y;

    } // createYPosition end

    protected double calculateDamage(int damageDone, byte shotType) {

        switch (shotType) {

            case 0:
                return damageDone;

            case 1:
                return damageDone - (damageDone * (armor * 0.25));

            case 2:
                return damageDone - (damageDone * (magiceResist * 0.25));

            case 3:
                if (armor > magiceResist) {
                    return damageDone - (damageDone * (magiceResist * 0.25));
                } else {
                    return damageDone - (damageDone * (armor * 0.25));
                }

        }
        return damageDone;
    }

    /**
     *
     * @param damageDone
     * @param uniqueHit
     * @param shotType 0 = No Type, 1 = Physical Type, 2 = Magical Type, 3 = Adaptive Type
     * @return
     */
    public boolean hit(int damageDone, short uniqueHit, byte shotType) {

        boolean hitBefore = false;

        for (int i = 0; i < lastHitID.length; i++) {
            if (uniqueHit == lastHitID[i]) {
                hitBefore = true;
            }
        }

        if (!hitBefore) {
            lastHitID[lastHitTracker] = uniqueHit;
            lastHitTracker++;
            if (lastHitTracker == lastHitID.length) {
                lastHitTracker = 0;
            }
            health -= calculateDamage(damageDone, shotType);
            if (health <= 0) {
                dead = true;
            }
        }

        return hitBefore;

    }

    public void reduceArmor() {

        if (armor > 0) {
            armor--;
        }

    }

    public void knockback(int distancePushedBack) {
        xPosition = (short) (xPosition + distancePushedBack);
    }

    public void setDousedInWater(boolean dousedInWater) {
        this.dousedInWater = dousedInWater;
    }

    public void setDousedInOil(boolean dousedInOil) {
        this.dousedInOil = dousedInOil;
    }

    public void setSlowed(boolean slowed) {
        this.slowed = slowed;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void setBleeding(boolean bleeding) {
        this.bleeding = bleeding;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public short getAttackDamage() {
        return attackDamage;
    }

    public byte getAttackRange() {
        return attackRange;
    }

    public byte getAttackSpeed() {
        return attackSpeed;
    }

    public boolean isDead() {
        return dead;
    }

    public short getGoldDropped() {
        return goldDropped;
    }

    public float getHealth() { return health; }

    public short getHeight() {
        return height;
    }

    public boolean isFlying() { return flying; }

    public boolean isBoss() { return boss; }

    public short getWidth() {
        return width;
    }

    public RectF getHitbox() { return hitbox; }

    public void move(long fps) {
        if (slowed) {
            hitbox.left = (float) (hitbox.left - 1 * (movementSpeed * 0.75) / fps);
        } else {
            hitbox.left = hitbox.left - 1 * movementSpeed / fps;
        }
        hitbox.right = hitbox.left + width;
    }

    public void update(long fps) {

        if (fps == 0) {
            fps = 30;
        }

        if (hitbox.left > spotToStop + 5 && !frozen) {
            move(fps);
        }

        if (health > 0) {
            percentHealth = health / maxHealth;
        } else {
            percentHealth = 0;
        }

    }

    public void draw(Canvas canvas, Paint paint) {

//        canvas.drawBitmap(enemyImage, xPosition, yPosition, paint);

//        canvas.drawRect(xPosition, yPosition, xPosition + width, yPosition + height, paint);

        paint.setColor(Color.MAGENTA);
        canvas.drawRect(hitbox, paint);

        paint.setColor(Color.RED);

        canvas.drawRect(hitbox.left, hitbox.top - 30,
                hitbox.right, hitbox.top - 20, paint);

        paint.setColor(Color.GREEN);

        canvas.drawRect(hitbox.left, hitbox.top - 30,
                hitbox.left + (width * percentHealth), hitbox.top - 20, paint);

    }
}
