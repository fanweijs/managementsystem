package com.company.utils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 咸赛
 * 系统时间
 * on 2017/12/18.
 */
public class TimeFrame extends JFrame implements Runnable {
    private JFrame frame;
    private JPanel timePanel;
//    private JLabel timeLabel;
    private JLabel timeLabel;
    private String DEFAULT_TIME_FORMAT = "YYYY 年 MM 月 dd 日   HH:mm:ss";
    private int ONE_SECOND = 1000;

    public TimeFrame()
    {
        timePanel = new JPanel();
//        timeLabel1 = new JLabel("当前时间: ");
        timeLabel = new JLabel();

//        timePanel.add(timeLabel1);
        timePanel.add(timeLabel);
        this.add(timePanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(500,400));
        this.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        while(true)
        {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
            timeLabel.setText(dateFormatter.format(
                    Calendar.getInstance().getTime()));
            try
            {
                Thread.sleep(ONE_SECOND);
            }
            catch(Exception e)
            {
                timeLabel.setText("Error!!!");
            }
        }
    }

    public static void main(String arg[])
    {
        TimeFrame tf2 =new TimeFrame();
        tf2.setVisible(true);

        Thread thread1=new Thread(tf2);
        thread1.start();
    }
}
