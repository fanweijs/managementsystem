package com.company.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 柏欢欢 on 2017/12/20.
 */
public class ImagePanel extends JPanel{
    private Integer width;
    private Integer heigth;
    private String imgPath;

    public ImagePanel(Integer width, Integer heigth, String imgPath) {
        this.width = width;
        this.heigth = heigth;
        this.imgPath = imgPath;
        this.setPreferredSize(new Dimension(this.width,this.heigth));
    }

    public ImagePanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/"+ imgPath));
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.width,this.heigth,Image.SCALE_FAST));
        imageIcon.paintIcon(this,g,0,0);
    }
}
