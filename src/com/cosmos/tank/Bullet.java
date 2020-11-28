package com.cosmos.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private static Dir dir;
    private final static int SPEED = 10;
    private final static int WIDTH = 5;
    private final static int HEIGHT = 15;

    // 构造方法
    Bullet(int x, int y, Dir dir){
        this.x = x;
        this.y = y;
        Bullet.dir = dir;
    }

    public void setDir(Dir dir){
        Bullet.dir = dir;
    }

    public Dir getDir(){
        return dir;
    }

    // 封装自动paint(画出自己的位置）
    public void paint(Graphics g){

        Color color = g.getColor();
        g.setColor(Color.RED);
        // 设定子弹形状
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
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
    }
}
