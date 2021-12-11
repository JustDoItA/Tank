package com.study.pattern;

import com.study.tank.abstracfactory.BaseTank;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
            int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
            int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

            Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            tank.getTf().gf.createBullet(bX,bY,dir,tank.getGroup(),tank.getTf());
        }

            //new Bullet(bX,bY,tank.getDir(),tank.getGroup(),tank.getTf());

            if(tank.getGroup() == Group.GOOD){
                new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
