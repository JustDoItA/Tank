package com.study.pattern;

import com.study.tank.abstracfactory.BaseTank;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank extends BaseTank {
    private int x,y;
    private  Dir dir = Dir.DOWN;
    private static final int SPEED =1;

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private Random random = new Random();

    private boolean moving = true;

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    private TankFrame tf = null;

    //public Rectangle rect = new Rectangle();

    private boolean living = true;
    //private Group group =  Group.BAD;
    private FireStrategy fs ;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.height = HEIGHT;
        rect.width = WIDTH;
        if(this.getGroup() == Group.GOOD){
            String goodFSName = (String)PropertyMgr.get("goodFireStrategy");
            //goodFSName代表的类load到内存,并且实例化
            try {
                fs = (FireStrategy) Class.forName(goodFSName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            String badFSName = (String)PropertyMgr.get("badFireStrategy");
            try {
                fs = (FireStrategy) Class.forName(badFSName).getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        /*Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x,y,50,50);
        g.setColor(c);*/
        if(!living)
            tf.tanks.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving)
            return;
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y +=  SPEED;
                break;
        }

        if(this.group == Group.BAD && random.nextInt(100)>95){
            this.fire();
        }
        //随机方向
        if(this.group == Group.BAD && random.nextInt(100)>95) {
            randomDir();
        }
        boundsCheck();
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2) {
            x = 2;
        }
        if(this.y < 28){
            y = 28;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH -2){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2){
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT -2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[ random.nextInt(4)];
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    //fireStrategy两种嗲用方式
    //1.作为参数传入，每次调用都要new，因此应该把DefaultFireStrategy设置为单例
    //2.作为类变量
    public void fire() {
        //fs.fire(this);
        int bX = this.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            this.getTf().gf.createBullet(bX,bY,dir,this.getGroup(),this.getTf());
        }

        //new Bullet(bX,bY,tank.getDir(),tank.getGroup(),tank.getTf());

        if(this.getGroup() == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }

    }

    public void die() {
        this.living = false;
    }
}