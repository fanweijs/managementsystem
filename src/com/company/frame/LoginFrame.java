package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.service.UserService;
import com.company.ui.ImagePanel;
import com.company.ui.RounderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class LoginFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private UserService userService = ServiceFactory.getUserSerivceInstance();

    public LoginFrame() {
        setVisible(true);
        setTitle("登录界面");
        setSize(new Dimension(2200,1100));
        setLocationRelativeTo(null);
        mainPanel = new ImagePanel(2200,1100,"bg.jpg");
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,800,200));
        add(mainPanel);
        mainPanel.add(centerPanel);
        centerPanel.setPreferredSize(new Dimension(700,600));
        centerPanel.setBackground(new Color(255,255,255,100));//设置透明度
//        Color color = new Color(255,255,255,100);
//        centerPanel = new RounderPanel(color,300);
        loginButton.setBorder(null);
        textField1.setBorder(null);
        passwordField1.setBorder(null);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(58,94,173));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(4,173,132));
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String account = textField1.getText();
                String password = new String(passwordField1.getPassword());
                Map<String,Object> map = userService.adminLogin(account, password);
                String info = (String) map.get("info");
                loginButton.setBackground(new Color(58,94,173));
                System.out.println(info);
                if ("登录成功".equals(info)) {
                    new MainFrame(account);
                   // LoginFrame.this.dispose();
               }else {
                   JOptionPane.showMessageDialog(null,info);
               }


            }
        });
    }

    public static void main(String[] args) {
         new LoginFrame();

    }
}
