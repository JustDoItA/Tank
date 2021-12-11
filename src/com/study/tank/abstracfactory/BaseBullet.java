package com.study.tank.abstracfactory;

import com.study.pattern.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract  void collideWith(BaseTank tank);

    public abstract void paint(Graphics g);
}
