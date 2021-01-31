package com.cosmos.tank.cor;

import com.cosmos.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
