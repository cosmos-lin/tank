package com.cosmos.tank;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    // 抽象出GrameObject父类
    public int x, y;

    public abstract int getWidth();
    public  abstract int getHeight();

    public abstract void paint(Graphics g);
}
