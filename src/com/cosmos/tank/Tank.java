package com.cosmos.tank;

import java.awt.*;

public class Tank{
    private int x,y;
    static Dir dir = Dir.DOWN;
    private final static int SPEED = 5;
    static boolean move = false;

    Tank(int x, int y, Dir dir){
        super();
        this.x = x;
        this.y = y;
        Tank.dir = dir;
    }

    public static void setDir(Dir dir) {
        Tank.dir = dir;
    }

    public static Dir getDir() {
        return dir;
    }

    public static void setMove(boolean move) {
        Tank.move = move;
    }

    public boolean isMoving(){
        return move;
    }

    // 重写paint方法；paint方法是窗口绘制时系统自动调用(每次绘制都会调用)
    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50); // 绘制一个矩形
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
