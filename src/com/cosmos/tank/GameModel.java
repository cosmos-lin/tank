package com.cosmos.tank;

import com.cosmos.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    // new Tank对象时，将自己传入Tank，从而让Tank可以引用TankFrame
//    private Tank myTank = new Tank(200, 200, Dir.DOWN, Group.GOOD, this);

    private static final GameModel INSTANCE = new GameModel();
    static {
        INSTANCE.init();
    }
    Tank myTank;

    // bullets、tank、explode的对象都添加到GameObject父类队列

    private List<GameObject> objects = new ArrayList<>();

    // new chain
    ColliderChain chain = new ColliderChain();
    // 返回抽象类的子类
    public static GameModel getInstance(){
        return INSTANCE;
    }

    public GameModel(){};

    public void init(){
        // 初始化主站坦克
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++){
            add(new Tank(50+i*80, 100, Dir.DOWN, Group.BAD));
        }
        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    public void add(GameObject go){
       this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g){
        myTank.paint(g);
        Color color = g.getColor();
        g.setColor(color.WHITE);
//        g.drawString("子弹数量：" + bullets.size(),10, 60);
//        g.drawString("敌方坦克数量：" + tanks.size(),10, 80);
//        g.drawString("爆炸数量：" + explodes.size(),10, 100);
        g.setColor(color);

        // 遍历父类容器，画出每个子类对象
        for (int i = 0; i < objects.size(); i++){
            objects.get(i).paint(g);
        }

        // 对容器中的对象进行碰撞检测
        for (int i = 0; i < objects.size(); i++){
            for (int j = i+1; j < objects.size(); j++){
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }

    }

    public Tank getMainTank() {

        return myTank;
    }
}
