package com.cosmos.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Frame 窗口
        TankFrame tankFrame = new TankFrame();
        // 获取配置tank数量
//        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
//        for (int i=0; i<initTankCount; i++){
//
//            //  初始化敌方坦克
//            tankFrame.tanks.add(new Tank(50+i*80, 100, Dir.DOWN, tankFrame,Group.BAD));
//        }

        while (true){
            Thread.sleep(25);
            // repaint会自动调用paint方法
            tankFrame.repaint();
        }
    }
}
