package com.company.frame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定 权限管理面板
 */

public class PermissionPanel extends JPanel{
    private List<String>permissionList = new ArrayList<>();
    public PermissionPanel(List<String>permissionList){
        this.permissionList=permissionList;
    }
}
