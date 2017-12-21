package com.company.frame;

import javax.swing.*;
import java.util.List;

public class InfoPanel extends JPanel {
    private List<String>infoList=null;

    public InfoPanel(List<String>infoList){
        this.infoList=infoList;
        JLabel jLabel  =new JLabel();
        StringBuffer stringBuffer = new StringBuffer();
        infoList.forEach(s ->stringBuffer.append(s) );
        jLabel.setText(new String(stringBuffer));
        add(jLabel);
    }
}
