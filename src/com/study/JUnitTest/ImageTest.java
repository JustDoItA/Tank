package com.study.JUnitTest;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;//断言

public class ImageTest {

    @Test
    void test(){
        try {
            //图片加载,路径写死不利于后期维护
            BufferedImage image = ImageIO.read(new File("D:\\java\\SPRING-SRC\\Tank\\sources\\1.bmp"));
            //assertNotNull(image);

            BufferedImage images = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(images);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //fail("Not yet implemented");
        //assertNotNull(new Object());
    }



}
