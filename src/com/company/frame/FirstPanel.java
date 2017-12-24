package com.company.frame;

import com.company.utils.ImagePanel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;

/**
 * Created by lihongyu on 2017/12/22.
 */
public class FirstPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel upPanel,downPanel;
    private JPanel panel1,panel2,panel3;
    private JScrollPane sp;
    private JLabel bgLabel,label1,label2;
    private JLabel[] labels;
    private JTextArea ta1,ta2;
    private Font smallBoldFont,bigBoldFont;
    private JList list;
    private String[] info = {"      公司简介","       最新通知","      加入我们"};

    public JPanel getMainPanel() {
        smallBoldFont = new Font("微软雅黑", Font.BOLD, 16);
        bigBoldFont = new Font("微软雅黑", Font.BOLD, 22);
        labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(info[i]);
        }
        mainPanel = new JPanel();
        upPanel = new JPanel();
        downPanel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();


        ta1 = new JTextArea(8,10);
        ta1.setText("万才科技有限公司是一家社会创投型企业。公司始创于2002年，主要致力于面向中小型企业的办公系统，公司总部位于北京。");
        ta1.setFont(smallBoldFont);
        ta1.setLineWrap(true);
        panel1.setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon(FirstPanel.class.getResource("/img/5.jpg"));
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(500,250,Image.SCALE_DEFAULT));
//        label2 = new JLabel(new ImageIcon(FirstPanel.class.getResource("/img/5.jpg")));
        label1= new JLabel();
        label1.setIcon(imageIcon);
        labels[0].setFont(bigBoldFont);
        panel1.add(labels[0], BorderLayout.NORTH);
        panel1.add(label1,BorderLayout.CENTER);
        panel1.add(ta1,BorderLayout.SOUTH);


        JScrollPane sp =new JScrollPane();
        sp.setPreferredSize(new Dimension(200,150));
        String[] info = {"1.学习十九大精神。","2.学习二十大。",
                "3.学习21大。","4.学习20大。"};
        JList list = new JList(info);
        list.setFont(smallBoldFont);
        sp.setViewportView(list);
        labels[1].setFont(bigBoldFont);
        panel2.setLayout(new BorderLayout());
        panel2.add(labels[1],BorderLayout.NORTH);
        panel2.add(sp,BorderLayout.CENTER);


        ta2 = new JTextArea(8,10);
        ta2.setText("1.负责集团的品牌包装与维护;2.独立完成营销媒体关系维护;3.完成公司新闻动态的撰写与发布。");
        ta2.setFont(smallBoldFont);
        ta2.setLineWrap(true);
        panel3.setLayout(new BorderLayout());
      ImageIcon imageIcon1 = new ImageIcon(FirstPanel.class.getResource("/img/5.jpg"));
      imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(500,250,Image.SCALE_DEFAULT));
//        label2 = new JLabel(new ImageIcon(FirstPanel.class.getResource("/img/5.jpg")));
        label2= new JLabel();
        label2.setIcon(imageIcon);
        label2.setSize(new Dimension(500,250));
       // ImagePanel imagePanel2 = new ImagePanel(500,250,"5.jpg");
        labels[2].setFont(bigBoldFont);
        panel3.add(labels[2], BorderLayout.NORTH);
        panel3.add(label2,BorderLayout.CENTER);
        panel3.add(ta2,BorderLayout.SOUTH);


//        bgLabel = new JLabel(new ImageIcon(FirstPanel.class.getResource("/img/1.jpg")));
//        bgLabel.setPreferredSize(new Dimension(600, 250));
        bgLabel = new JLabel();
        ImagePanel imagePanel = new ImagePanel(1150,350,"1.jpg");
        upPanel.add(imagePanel);
        downPanel.setLayout(new GridLayout(1,3,50,50));
        downPanel.add(panel1);
        downPanel.add(panel2);
        downPanel.add(panel3);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(upPanel,BorderLayout.NORTH);
        mainPanel.add(downPanel);
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(new FirstPanel().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

