package com.cosmos.tank.abstractfactory;

import com.cosmos.tank.Dir;
import com.cosmos.tank.Group;
import com.cosmos.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
