package com.study.pattern;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

//图片内容加载进来
public class ResourceMgr {
    public  static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    public  static BufferedImage badTankL,badTankU,badTankR,badTankD;
    public  static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public  static BufferedImage[] explodes = new BufferedImage[16];

    //单例模式：实例初始化,并且只有内部可以访问
    //枚举反编译后是抽象类，防止反序列化
    private static final ResourceMgr resourceMgr = new ResourceMgr();
    //单例模式：防止外部new出实例
    private ResourceMgr(){

    }
    //单例模式：想要获取该实例只有这么一种方式
    public static synchronized ResourceMgr getInstance(){
        return resourceMgr;
    }

    static{
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankD = ImageUtil.rotateImage(badTankU,180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);;
            bulletD = ImageUtil.rotateImage(bulletU,180);;

            //爆炸的图片
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
