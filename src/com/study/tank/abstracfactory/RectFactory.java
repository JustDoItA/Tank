package com.study.tank.abstracfactory;

import com.study.pattern.Bullet;
import com.study.pattern.Dir;
import com.study.pattern.Group;
import com.study.pattern.TankFrame;

public class RectFactory extends GameFactory{
    @Override
    public BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new ReactTank(x,y,dir,group,tankFrame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x,y,tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new RectBullet(x,y,dir,group,tankFrame);
    }


}
