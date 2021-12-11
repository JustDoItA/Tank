package com.study.pattern;

import com.study.tank.abstracfactory.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200,600,Dir.DOWN,Group.GOOD,this);
    public List<BaseBullet> bullets = new ArrayList();
    public List<BaseTank> tanks = new ArrayList<>();
    public List<BaseExplode> explodes = new ArrayList<>();
    Explode e = new Explode(100,100,this);

    //public GameFactory gf = new RectFactory();
    public GameFactory gf = new DefaultFactory();

    Bullet bullet = new Bullet(300,300,Dir.DOWN,Group.GOOD,this);
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 800;

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        //添加键盘监听事件
        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //双缓冲 闪烁现象解决 先把图片放到内存 然后通过把内存图片写到显存里
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_WIDTH);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量"+bullets.size(),10,50);
        g.drawString("敌人的数量"+tanks.size(),10,80);
        g.drawString("爆炸的数量"+explodes.size(),10,100);
        g.setColor(c);
        myTank.paint(g);
        //foreach 不允许中间进行其它方式其它方式的删除，传统方式每次都会重新计算容器长度
        for (int i=0; i<bullets.size();i++) {
            bullets.get(i).paint(g);
        }
        //敌方坦克
        for (int i=0; i<tanks.size();i++) {
            tanks.get(i).paint(g);
        }
        //爆炸描绘
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);

        }

        // 碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }



        //e.paint(g);
    }

    //键盘监听内部处理类
    class MyKeyListener extends KeyAdapter {

        //根据玩家按键的状态判断坦克运动方向
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();//获取键的代码
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
            //x += 200;
            //repaint();//重新刷新窗口
            //System.out.println("key press");
        }

        @Override
        public void keyReleased(KeyEvent e){

            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL && !bU && !bR && !bD){
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}