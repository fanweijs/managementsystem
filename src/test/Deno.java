package test;

import com.company.model.Login;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Deno extends JFrame {
    private JScrollPane jScrollPane ;
    private JList<Login>loginJList;
    private DefaultListModel<Login> model = new DefaultListModel<>();
    public Deno(){
        Login login = new Login("1001","11111");
        Login login1 = new Login("1001","11111");
        Login login2 = new Login("1001","11111");
        Login login3= new Login("1001","11111");
        loginJList = new JList<>();
        model.addElement(login);
        model.addElement(login1);
        model.addElement(login2);
        model.addElement(login3);


        loginJList.setModel(model);
        jScrollPane = new JScrollPane(loginJList);
        add(jScrollPane);
        setSize(400,500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Deno();
    }
}
