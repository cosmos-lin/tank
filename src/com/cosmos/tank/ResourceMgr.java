package com.cosmos.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage GoodtankL, GoodtankR, GoodtankU, GoodtankD;
    public static BufferedImage BadtankL, BadtankR, BadtankU, BadtankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            GoodtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            GoodtankR = ImageUtil.rotateImage(GoodtankU, 90);
            GoodtankL = ImageUtil.rotateImage(GoodtankU, -90);
            GoodtankD = ImageUtil.rotateImage(GoodtankU, 180);

            BadtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            BadtankR = ImageUtil.rotateImage(BadtankU, 90);
            BadtankL = ImageUtil.rotateImage(BadtankU, -90);
            BadtankD = ImageUtil.rotateImage(BadtankU, 180);

             bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
             bulletR = ImageUtil.rotateImage(bulletU, 90);
             bulletL = ImageUtil.rotateImage(bulletU, -90);
             bulletD = ImageUtil.rotateImage(bulletU, 180);

             for (int i=0; i<16; i++) {
                 explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
