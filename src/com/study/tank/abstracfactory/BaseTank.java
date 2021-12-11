package com.study.tank.abstracfactory;

import com.study.pattern.Group;

import java.awt.*;

public abstract class BaseTank {

    public Group group = Group.BAD;

    public abstract void paint(Graphics g);

    public  Group getGroup(){
     return this.group;
    }
    public Rectangle rect = new Rectangle();

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

    //public abstract void collideWith(BaseTank baseTank);
}
