package com.cosmos.tank.cor;

import com.cosmos.tank.GameObject;
import com.cosmos.tank.Tank;
import com.cosmos.tank.Wall;

public class TankWallCollier implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2){
        if (o1 instanceof Tank && o2 instanceof Wall){
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if (tank.getRect().intersects(wall.rect)){
                tank.handle_collide();
            }
        }else if (o1 instanceof Wall && o2 instanceof Tank){
            return collide(o2, o1);
        }
        return true;
    }
}
