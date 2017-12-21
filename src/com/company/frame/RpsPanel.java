package com.company.frame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RpsPanel extends JPanel {
    private List<String>rpsList = new ArrayList<>();
    public RpsPanel(List<String>rpsList){
        this.rpsList= rpsList;
    }

}
