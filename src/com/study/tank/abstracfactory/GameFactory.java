package com.study.tank.abstracfactory;

import com.study.pattern.Dir;
import com.study.pattern.Group;
import com.study.pattern.TankFrame;

public abstract class GameFactory {

    public abstract BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
    public abstract BaseBullet createBullet(int x,int y ,Dir dir,Group group,TankFrame tankFrame);


}
