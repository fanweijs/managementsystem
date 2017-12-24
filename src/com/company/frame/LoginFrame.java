package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class LoginFrame extends JFrame{
    private JPanel mainPanel;
    private JButton 登陆Button;
    private JPanel centerPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private UserService userService = ServiceFactory.getUserSerivceInstance();

    public LoginFrame() {
        add(mainPanel);
        mainPanel = new bgPanel();
        mainPanel.setLayout( new FlowLayout(FlowLayout.CENTER,250,250));
        textField1.setPreferredSize(new Dimension(150,30));
        passwordField1.setPreferredSize(new Dimension(150,30));
        centerPanel.setPreferredSize(new Dimension(440,400));
        mainPanel.add(centerPanel);
        centerPanel.setSize(new Dimension(300,500));
        Color color = new Color(239,249,250,75);
        jPanel1.setBackground(color);
        jPanel2.setBackground(color);
        jPanel3.setBackground(color);
        jPanel4.setBackground(color);
        centerPanel.setBackground(color);

        setLocationRelativeTo(null);
        setSize(new Dimension(500,600));
        登陆Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String account = textField1.getText();
                String password = new String(passwordField1.getPassword());
                Map<String,Object> map = userService.adminLogin(account, password);
                String info = (String) map.get("info");
                System.out.println(info);
               if ("登录成功".equals(info)) {
                    new MainFrame(account);
               }else {
                   JOptionPane.showMessageDialog(null,info);
               }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginFrame");
        frame.setContentPane(new LoginFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
class  bgPanel extends JPanel{
    @Override
    public void paintComponent(Graphics g) {
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/bg.jpg"));
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST));
        imageIcon.paintIcon(this, g, 0, 0);

    }
}
