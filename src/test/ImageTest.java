package test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertNotNull;

public class ImageTest {
    @Test
    void test(){
        try {
            // 指定图片加载到内存；
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif "));
//            BufferedImage image = ImageIO.read(new File("D:/1.gif")); 不灵活
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
