package com.cosmos.tank;

import java.awt.*;

public abstract class GameObject {
    // 抽象出GrameObject父类
    public int x, y;

    public abstract int getWidth();
    public  abstract int getHeight();

    public abstract void paint(Graphics g);
}
