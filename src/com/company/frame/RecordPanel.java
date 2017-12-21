package com.company.frame;



import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 档案管理模块 面板
 */
public class RecordPanel extends JPanel{
    private List<String>recordList = new ArrayList<>();

    public RecordPanel(List<String>recordList){
        this.recordList=recordList;
    }
}
