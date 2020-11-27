import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200, y = 200;
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
        x += 20;
 //        y += 20;
    }

    //定义内部类继承KeyAdapter（处理键盘事件类）
    static class MyKeyListener extends KeyAdapter{

        @Override
        // 监听key被按下时自动调 用
        public void keyPressed(KeyEvent e) {
//            System.out.println("keypress");
//            x += 10;
        }

        // 监听key弹起时自动调用
        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("keyreleased");
        }
    }
}
