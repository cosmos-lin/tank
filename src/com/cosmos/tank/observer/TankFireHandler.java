package com.cosmos.tank.observer;

import com.cosmos.tank.Tank;

public class TankFireHandler implements TankFireObserver{

    @Override
    public void actionOnFire(TankFireEvent event){
        Tank tank = event.getSource();
        tank.fire();
    }
}
