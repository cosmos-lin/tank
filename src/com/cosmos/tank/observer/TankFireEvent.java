package com.cosmos.tank.observer;

import com.cosmos.tank.Tank;

public class TankFireEvent {
    Tank tank;

    public Tank getSource(){
        return tank;
    }
    public TankFireEvent(Tank tank){
        this.tank = tank;
    }
}
