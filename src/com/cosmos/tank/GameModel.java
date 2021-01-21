package com.cosmos.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    // new Tank对象时，将自己传入Tank，从而让Tank可以引用TankFrame
    private Tank myTank = new Tank(200, 200, Dir.DOWN, Group.GOOD, this);

//    // 定义队列存储子弹
//    java.util.List<Bullet> bullets = new ArrayList<>();
//    // 定义队列存储敌方坦克
//    java.util.List<Tank> tanks = new ArrayList<>();
//    // 定义存储爆炸队列
//    List<EXplode> explodes = new ArrayList<>();

    // bullets、tank、explode的对象都添加到GameObject父类队列
    private List<GameObject> objects = new ArrayList<>();

    public GameModel(){
        // 加载地方坦克数量
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++){
            add(new Tank(50+i*80, 100, Dir.DOWN, Group.BAD, this));
        }
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
        // 遍历子弹夹，绘制子弹
//        for (int i=0; i<bullets.size(); i++){
//            bullets.get(i).paint(g);
//        }
//
//        // 画出敌方坦克
//        for (int i = 0; i < tanks.size(); i++) {
//            tanks.get(i).paint(g);
//        }
//
//        // 画出爆炸画面
//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }

        // 遍历每颗子弹和坦克，进行碰撞检测
//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }

    }

    public Tank getMainTank() {

        return myTank;
    }
}
