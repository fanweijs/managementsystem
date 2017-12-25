package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.model.RewardPublish;
import com.company.service.RpService;
import com.company.utils.DialogDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 柏欢欢 on 2017/12/23.
 */
public class NewRp extends JFrame{
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField accountField;
    private JComboBox flagBox;
    private JComboBox nameBox;
    private JButton timeButton;
    private JButton addButton;
    private JButton cancelButton;
    private JLabel timeLabel;
    private RpPanel rpPanel;
    private RpService rpService = ServiceFactory.getRpServiceInstance();
    private String flagString;
    private String nameString;
    private String dateString;
    private Map<String, java.util.List<String>> map;

    public NewRp(RpPanel rpPanel){
        this.rpPanel = rpPanel;
        setTitle("新增奖惩信息");
        setSize(650,700);
        setLocationRelativeTo(null);
        setVisible(true);
        add(mainPanel);
        map = RpData.init();
        for (Map.Entry<String, java.util.List<String>> entry:map.entrySet()) {
            flagBox.addItem(entry.getKey());
        }
        for (String str:map.get(flagBox.getSelectedItem())) {
            nameBox.addItem(str);
        }
        flagBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                flagString = (String)flagBox.getSelectedItem();
                nameBox.removeAllItems();
                for (String str:map.get(flagBox.getSelectedItem())) {
                    nameBox.addItem(str);
                }
            }
        });
        nameBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               nameString =(String) nameBox.getSelectedItem();
            }
        });
        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtDate = new JTextField();
                //弹出时间选择框
                new DialogDatePicker(true, txtDate, 750, 450);
                //获得日期
                dateString = txtDate.getText();
                timeLabel.setText(dateString);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = idField.getText();
                String accountString = accountField.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(dateString);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                Date enrollment = new java.sql.Date(date.getTime());
                RewardPublish rewardPublish = new RewardPublish(Integer.valueOf(idString),accountString,flagString,nameString, (java.sql.Date) enrollment);
                int n = 0;
                n = rpService.insertRP(rewardPublish);
                if (n != 0) {
                    JOptionPane.showMessageDialog(null, "新增信息成功");
                    NewRp.this.dispose();
                    System.out.println(rewardPublish);
                    rpPanel.addRP(rewardPublish);
                } else {
                    JOptionPane.showMessageDialog(null, "新增信息失败");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "新增信息失败");
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addButton.setBackground(new Color(51,153,153));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addButton.setBackground(new Color(4,173,132));
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelButton.setBackground(new Color(51,153,153));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                cancelButton.setBackground(new Color(4,173,132));
            }
        });
    }

}
