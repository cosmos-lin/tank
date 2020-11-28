package com.cosmos.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
//    private static Dir dir; // 注意如果static修饰，子弹会随tank一起改变方向
    private Dir dir;
    private final static int SPEED = 10;
    private final static int WIDTH = 5;
    private final static int HEIGHT = 15;
    TankFrame tf = null;
    private boolean live = true;

    // 构造方法
    Bullet(int x, int y, Dir dir, TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    // 封装自动paint(画出自己的位置）
    public void paint(Graphics g){

        // live为false,删除子弹本身对象
        if (!live) {
            tf.bullets.remove(this);
        }
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
        // 定义子弹存活条件
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) live = false;
    }
}
