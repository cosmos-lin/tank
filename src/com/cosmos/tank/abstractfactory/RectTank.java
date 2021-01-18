package com.cosmos.tank.abstractfactory;

import com.cosmos.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {

    private int x, y;
    public Dir dir = Dir.DOWN;
    private final static int SPEED = 2;
    public TankFrame tf = null;
    public static int WIDTH = ResourceMgr.GoodtankU.getWidth();
    public static int HEIGHT = ResourceMgr.GoodtankU.getHeight();

    private boolean moving = true;
    private boolean living = true;
    private Random random = new Random();
    public RectTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void fire(){
        int bX = this.x + RectTank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + RectTank.HEIGHT/2 - Tank.WIDTH/2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            tf.gf.createBullet(x, y, dir, group, tf);
        }
        if (group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();

    }

    public boolean isMoving(){
        return moving;
    }

    private void move() {

        if (!moving)
            return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();

        boundsCheck();
        // update rect
        rect.x = this.x;
        rect.y = this.y;

    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    private void boundsCheck() {
        if (this.x < 2)
            x = 2;
        if (this.y < 28)
            y = 28;
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2)
            x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2)
            y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2;
    }

    @Override
    public void paint(Graphics g){
        if (! living){
            tf.tanks.remove(this);
        }
        Color c = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        g.fillRect(x, y, 40, 40);
        g.setColor(c);
        move();
    }

    @Override
    public void die() {
        this.living = false;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
