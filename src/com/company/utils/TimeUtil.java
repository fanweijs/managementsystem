package com.company.utils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeUtil implements Runnable {
    private JLabel jLabel = new JLabel();
    public TimeUtil(JLabel jLabel){
        this.jLabel = jLabel;
    }
    @Override
    public void run() {
        while (true){
            Date date = new Date();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeString = sdf.format(date);
            jLabel.setText(timeString);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
