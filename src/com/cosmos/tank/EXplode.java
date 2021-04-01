package com.cosmos.tank;

import java.awt.*;

public class EXplode extends GameObject{
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private boolean living = true;

    private int step = 0;

    public EXplode(int x, int y){
        this.x = x;
        this.y = y;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
        GameModel.getInstance().add(this);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public void paint(Graphics g) {
        // 爆炸状态为false,移出队列
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length){
            step = 0;
            // 画完爆炸效果后移除队列
            GameModel.getInstance().remove(this);
        }

    }
}
