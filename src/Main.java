import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Frame 窗口
        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);
            // repaint会自动调用paint方法
            tankFrame.repaint();
        }
    }
}
