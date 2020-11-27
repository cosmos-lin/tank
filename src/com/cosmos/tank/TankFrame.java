package com.cosmos.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    private int x = 200, y = 200;
    static Dir dir = Dir.DOWN;
    private final static int SPEED = 10;
    /*
    继承Frame类，定义构造方法
     */
    public TankFrame() {

        // setVisible(true)设置为可见
        setVisible(true);
        // 设置窗口大小
        setSize(800, 600);
        //设置窗口为不可变
        setResizable(false);
        setTitle("tank war");

        // 调用自定义key监听内部类
        this.addKeyListener(new MyKeyListener());

        // 添加window监听事件，点击窗口退出按钮，执行退出
        // new WindowAdapter() 匿名内部类
        /*
        addWindowListener 要求我们传入WindowListener;此处我们,new WindowAdapter是实现了
        windowListener的接口（可以认为是windowListener的子类);重写windowClosing方法，实现系统退出功能
         */
        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    // 重写paint方法；paint方法是窗口绘制时系统自动调用(每次绘制都会调用)
    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x, y, 50, 50); // 绘制一个矩形

        switch (dir){
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            case LEFT:
                x+=SPEED;
                break;
            case RIGHT:
                x-=SPEED;
                break;
        }
    }

    //定义内部类继承KeyAdapter（处理键盘事件类）
    static class MyKeyListener extends KeyAdapter{

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;
        @Override
        // 监听key被按下时自动调 用
        public void keyPressed(KeyEvent e) {

            // 判断key状态
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default: // 默认不匹配break
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bL) dir = Dir.LEFT;
            if (bD) dir = Dir.DOWN;
            if (bR) dir = Dir.RIGHT;
            if (bU) dir = Dir.UP;
        }

        // 监听key弹起时自动调用
        @Override
        public void keyReleased(KeyEvent e) {

            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
    }
}
