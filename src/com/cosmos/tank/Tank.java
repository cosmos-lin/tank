package com.cosmos.tank;

import java.awt.*;

public class Tank{
    private int x,y;
    static Dir dir = Dir.DOWN;
    private final static int SPEED = 5;
    static boolean move = false;
    private TankFrame tf = null;
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    Tank(int x, int y, Dir dir, TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    // 子弹发射方法(调用fire;new一颗子弹add到子弹容器)
    public void fire() {
        // 计算子弹在坦克中心位置发出
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        // 将TankFrame 传给Bullet; Bullet可以继续引用TankFrame
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
    }

    public boolean isMove(){
        return move;
    }

    // 重写paint方法；paint方法是窗口绘制时系统自动调用(每次绘制都会调用)
    public void paint(Graphics g) {
        // 读取tank图片
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
        moving();
    }

    private void moving() {
        // move为false,不进行移动
        if (!move) return;

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
    }
}
