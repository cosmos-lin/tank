package com.cosmos.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
//    private static Dir dir; // 注意如果static修饰，子弹会随tank一起改变方向
    private Dir dir;
    private final static int SPEED = 10;
    TankFrame tf = null;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    public static boolean living = true;

    // 构造方法
    Bullet(int x, int y, Dir dir, TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    // 封装自动paint(画出自己的位置）
    public void paint(Graphics g){

        // living为false,删除子弹本身对象
        if (!living) tf.bullets.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        moving();

    }

    // 封装子弹移动方法
    private void moving() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
        // 定义子弹存活条件
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) living = false;
    }

    // 定义碰撞检测方法
    public void collideWith(Tank tank) {
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect1.intersects(rect2)) {
            living = false;
            tank.die();
            this.die();
        }
    }

    private void die() {
        living = false;
    }
}
