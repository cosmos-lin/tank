package com.cosmos.tank;

public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        // 计算子弹在坦克中心位置发出
        int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        new Bullet(bX, bY, t.dir, t.tf, t.group);

        // 定义爆炸声音
        if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
