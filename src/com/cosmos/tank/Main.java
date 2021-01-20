package com.cosmos.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Frame 窗口
        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(25);
            // repaint会自动调用paint方法
            tankFrame.repaint();
        }
    }
}
