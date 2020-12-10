package com.cosmos.tank;

import java.awt.*;

public class EXplode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private boolean living = true;
    TankFrame tf = null;

    private int step = 0;

    public EXplode(int x, int y, TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        // 爆炸状态为false,移出队列
        if (!living) tf.explodes.remove(this);
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length){
            step = 0;
        }
        // living 初始化为true，画完爆炸效果后置为false
        living = false;

    }
}
