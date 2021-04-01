package com.cosmos.tank.decorator;

import com.cosmos.tank.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    GameObject go;
    public GODecorator(GameObject go){
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g);

}
