package com.cosmos.tank;

import com.cosmos.tank.decorator.RectDecorator;
import com.cosmos.tank.decorator.TailDecorator;

public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        // 计算子弹在坦克中心位置发出
        int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        //Bug? new Bullet把自己加了一遍
        GameModel.getInstance().add(new RectDecorator(
                new TailDecorator(
                        new Bullet(bX, bY, t.dir, t.group)
                )
        ));
        // 定义爆炸声音
        if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
