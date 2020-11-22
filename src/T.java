import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {
    public static void main(String[] args) {
        // Frame 窗口
        Frame frame = new Frame();
        // setVisible(true)设置为可见
        frame.setVisible(true);
        // 设置窗口大小
        frame.setSize(800, 600);
        // 设置窗口为不可变
        frame.setResizable(false);
        frame.setTitle("tank war");

        // 添加window监听事件，点击窗口退出按钮，执行退出
        // new WindowAdapter() 匿名内部类
        /*
        addWindowListener 要求我们传入WindowListener;此处我们,new WindowAdapter是实现了
        windowListener的接口（可以认为是windowListener的子类);重写windowClosing方法，实现系统退出功能
         */
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


    }
}
