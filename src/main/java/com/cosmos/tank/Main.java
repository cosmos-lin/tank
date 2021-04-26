package com.cosmos.tank;

import com.cosmos.tank.net.Client;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Frame 窗口
        TankFrame tankFrame = TankFrame.INSTANCE;
        tankFrame.setVisible(true);
        // 获取配置tank数量
//        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
//        for (int i=0; i<initTankCount; i++){
//
//            //  初始化敌方坦克
//            tankFrame.tanks.add(new Tank(50+i*80, 100, Dir.DOWN, tankFrame,Group.BAD));
//        }

        // music
        new Thread(() -> new Audio("autio/war1.wav").loop()).start();

        new Thread(() -> {
            while (true){
                try{
                    Thread.sleep(25);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                tankFrame.repaint();
            }
        }).start();
        // 连接服务器
        Client c = new Client();
        c.connect();
    }
}
