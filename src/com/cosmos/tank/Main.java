package com.cosmos.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Frame 窗口
        TankFrame tankFrame = new TankFrame();
        for (int i=0; i<5; i++){

            //  初始化敌方坦克
            tankFrame.tanks.add(new Tank(50+i*80, 100, Dir.DOWN, tankFrame));
        }

        while (true){
            Thread.sleep(50);
            // repaint会自动调用paint方法
            tankFrame.repaint();
        }
    }
}
