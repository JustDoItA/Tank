package com.study.pattern;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

        //坦克数量
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));


        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(tf.gf.creatTank(50+i*80,200,Dir.DOWN,Group.BAD,tf));
        }

        //循环播放背景音乐
        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tf.repaint();
        }
    }
}
