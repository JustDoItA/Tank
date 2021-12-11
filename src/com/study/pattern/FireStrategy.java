package com.study.pattern;

import com.study.tank.abstracfactory.BaseTank;

//开火策略
public interface FireStrategy {
    public void fire(Tank t);
}
