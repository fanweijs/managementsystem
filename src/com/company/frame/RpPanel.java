package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.model.RewardPublish;
import com.company.service.RpService;
import com.company.utils.ExportExcel;
import com.company.ui.MyMouseAdapter;
import com.company.ui.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 柏欢欢 on 2017/12/21.
 * 奖惩管理界面功能编写
 */
public class RpPanel extends JPanel implements ActionListener {
    private List<String> rpList = new ArrayList<>();
    private CardLayout card = new CardLayout();
    private JPanel centrePanel = new JPanel();
    private String account;
    private RpService rpService = ServiceFactory.getRpServiceInstance();
    private JList<RewardPublish> rlist,plist;
    private DefaultListModel<RewardPublish> rdefaultListModel,pdefaultListModel ;
    private StringBuffer condition = new StringBuffer();
    private DefaultTableModel dtm;
    private JTable table = new JTable();;
    private int[] rows;
    private List<RewardPublish> rps;
    private String flag = null;
    private JButton[]jButtons;

    public RpPanel(List<String> rpList,String account) {
        this.rpList = rpList;
        this.account = account;
        init();
    }

    public void init(){
       jButtons = new JButton[rpList.size()];
        int i=0;
        for ( String str:rpList) {
            jButtons[i]= new JButton(str);
            jButtons[i].addActionListener(this);
            i++;
        }
        this.setLayout(new BorderLayout());
        JPanel westPanel = new JPanel();
        westPanel.setBackground(new Color(153,153,153));
        westPanel.setLayout(new GridLayout(9,1,0,50));
        JLabel jLabel = new JLabel();
        westPanel.add(jLabel);
        for(int rp =0; rp < jButtons.length; rp++){
            westPanel.add(jButtons[rp]);
            Style.setItemButtonStyle(jButtons[rp]);
           jButtons[rp].addMouseListener(new MyMouseAdapter(jButtons[rp]));
        }

        add(BorderLayout.WEST,westPanel);
        centrePanel.setLayout(card);
        /**
         * 个人奖惩 card1
         */
        JPanel card1= new JPanel();
        card1.setLayout(new GridLayout(1,2,0,0));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(186,194,196));
        JLabel jLabel1 = new JLabel("个人奖励情况");
        jLabel1.setFont(new Font("微软雅黑",Font.PLAIN,27));
        topPanel.add(jLabel1);
        leftPanel.add(topPanel,BorderLayout.NORTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel upPanel = new JPanel();
        upPanel.setBackground(new Color(186,194,150));
        JLabel jLabel2 = new JLabel("个人惩罚情况");
        jLabel2.setFont(new Font("微软雅黑",Font.PLAIN,27));
        upPanel.add(jLabel2);
        rightPanel.add(upPanel,BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JPanel contentPanel2 = new JPanel();
        contentPanel2.setLayout(new BorderLayout());
        List<RewardPublish> list = rpService.getRP(account);
        rlist = new JList<>();
        rdefaultListModel = new DefaultListModel<>();
        plist = new JList<>();
        pdefaultListModel = new DefaultListModel<>();
        for (RewardPublish rp : list) {
            if ("奖".equals(rp.getFlag())) {
                rdefaultListModel.addElement(rp);
                rlist.setFont(new Font("", Font.PLAIN, 22));
            }
            if ("惩".equals(rp.getFlag())) {
                pdefaultListModel.addElement(rp);
                plist.setFont(new Font("",Font.PLAIN,22));
            }
        }
        rlist.setModel(rdefaultListModel);
        plist.setModel(pdefaultListModel);
        JScrollPane jScrollPane = new JScrollPane(rlist);
        JScrollPane jScrollPane2 = new JScrollPane(plist);
        contentPanel.add(jScrollPane);
        contentPanel2.add(jScrollPane2);
        leftPanel.add(contentPanel);
        rightPanel.add(contentPanel2);
        card1.add(leftPanel);
        card1.add(rightPanel);

        /**
         * 奖惩管理 card2
         */
        JPanel card2 = new JPanel();
        card2.setLayout(new BorderLayout());
        JPanel topPanel2 = new JPanel(new GridLayout(1,3,0,0));
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,50,30));
        addPanel.setBackground(new Color(186,194,196));
        JButton addButton = new JButton("+新增");
        addButton.setBackground(new Color(4,173,132));
        addButton.setFont(new Font("微软雅黑",Font.PLAIN,20));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewRp(RpPanel.this);
            }
        });
        addPanel.add(addButton);
        JPanel boxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
        boxPanel.setBackground(new Color(186,194,196));
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("微软雅黑",Font.PLAIN,18));
        comboBox.setOpaque(false);
        comboBox.setBackground(null);
        comboBox.addItem("请选择筛选标记");
        comboBox.addItem("奖");
        comboBox.addItem("惩");
        boxPanel.add(comboBox);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    flag = comboBox.getSelectedItem().toString().trim();
                    if (condition.length() == 0) {
                        condition = new StringBuffer();
                        condition.append("WHERE flag = '" + flag + "'");
                    } else {
                        condition.append(" AND flag = '" + flag + "'");
                    }
                    //更新数据
                    updateModel();
                }
            }
        });
        //还原表格、重置查询条件
        card2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                condition = new StringBuffer();
                updateModel();
            }
        });
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,30));
        searchPanel.setBackground(new Color(186,194,196));
        JTextField textField = new JTextField();
        Style.setFieldStyle(textField);
        textField.setBounds(100,10,120,30);
        JButton searchButton = new JButton("搜索");
        searchButton.setBackground(new Color(4,173,132));
        searchButton.setFont(new Font("微软雅黑",Font.PLAIN,20));
        searchPanel.add(textField);
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = textField.getText().trim();
                rps = rpService.queryLike(keywords);
                int count = dtm.getRowCount();
                for (int i = count - 1; i >= 0; i--) {
                    dtm.removeRow(i);
                }
                for (RewardPublish rewardPublish : rps) {
                    addRP(rewardPublish);
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //选中多行
                rows = table.getSelectedRows();
            }
        });
        topPanel2.add(addPanel);
        topPanel2.add(boxPanel);
        topPanel2.add(searchPanel);
        card2.add(topPanel2,BorderLayout.NORTH);

        JPanel contentPanel3 = new JPanel();
        contentPanel3.setLayout(new BorderLayout());
        showRpTable();
        JScrollPane jScrollPane3 = new JScrollPane(table);
        contentPanel3.add(jScrollPane3);
        card2.add(contentPanel3,BorderLayout.CENTER);
        card2.revalidate();
        contentPanel3.revalidate();

        JPanel downPanel = new JPanel(new GridLayout(1,2,0,0));
        JPanel exportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,80,30));
        exportPanel.setBackground(new Color(186,194,196));
        JButton  exportButton = new JButton("导出数据");
        exportButton.setBackground(new Color(4,173,132));
        exportButton.setFont(new Font("微软雅黑",Font.PLAIN,20));
        exportPanel.add(exportButton);
        downPanel.add(exportPanel);
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 调用工具类，将list中数据写入指定路径的excel文件中
                JFileChooser fc = new JFileChooser("F:/");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int n = fc.showOpenDialog(RpPanel.this);
                String path = null;
                if (n == JFileChooser.APPROVE_OPTION) {
                    path = fc.getSelectedFile().getPath();
                    ExportExcel.exportData(rps, path);
                    JOptionPane.showMessageDialog(null, "导出成功");
                } else {
                    JOptionPane.showMessageDialog(null, "导出失败");
                }
            }
        });
        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,120,30));
        deletePanel.setBackground(new Color(186,194,196));
        JButton deleteButton = new JButton("删除");
        deleteButton.setBackground(new Color(4,173,132));
        deleteButton.setFont(new Font("微软雅黑",Font.PLAIN,20));
        deletePanel.add(deleteButton);
        downPanel.add(deletePanel);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> ids = new ArrayList<>();
                //遍历选中的多行
                for (int i : rows) {
                    System.out.println(i);
                    ids.add(Integer.valueOf(table.getValueAt(i, 0).toString()));
                }
                int[] result;
                result = rpService.bathchDeleteRP(ids);
                if (result.length != 0) {
                    JOptionPane.showMessageDialog(null, "删除奖惩信息成功");
                    //从表格模型中移除掉已经删除的记录
                    for (int i = rows.length - 1; i >= 0; i--) {
                        dtm.removeRow(rows[i]);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "删除奖惩失败");
                }
            }
        });
        card2.add(downPanel,BorderLayout.SOUTH);
        centrePanel.add(card1,"1");
        centrePanel.add(card2,"2");
        add(BorderLayout.CENTER,centrePanel);

    }
    /**
     * 显示奖惩信息表格数据
     */
    private void showRpTable() {
        table.setFont(new Font("",Font.PLAIN,20));
        table.setRowHeight(30);
        dtm = new DefaultTableModel();
        String[] titles = {"编号","员工号", "奖惩标记", "奖惩内容", "奖惩时间"};
        //设置表头的标题列
        dtm.setColumnIdentifiers(titles);
        //给表格设置数据模型
        table.setModel(dtm);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        // 将单元格内容居中
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        // 将表头居中
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(r1);
        table.getTableHeader().setPreferredSize(new Dimension(1,40));
        //内容字符串数组
        String[] content = new String[5];
        //获取到数据库中所有用户信息
        rps = rpService.getAllRP();
        Iterator<RewardPublish> iterator = rps.iterator();
        while (iterator.hasNext()) {
            RewardPublish rp = iterator.next();
            content[0] = String.valueOf(rp.getId());
            content[1] = rp.getAccount();
            content[2] = rp.getFlag();
            content[3] = rp.getRp_name();
            content[4] = rp.getRp_time().toString();
            dtm.addRow(content);
        }
        table.invalidate();
    }
    public void setUI(){
        for(JButton jButton :jButtons){
            Style.setItemButtonStyle(jButton);
        }
    }
    /**
     * 表格增加一行奖惩数据
     *
     * @param rp
     */
    public void addRP(RewardPublish rp) {
        Object[] rowData = { rp.getId(),rp.getAccount(),rp.getFlag(), rp.getRp_name(), rp.getRp_time()};
        dtm.addRow(rowData);
    }
    /**
     * 更新奖惩表格模型数据
     */
    public void updateModel() {
        rps = rpService.queryFilter(condition.toString());
        int count = dtm.getRowCount();
        for (int i = count - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
        for (RewardPublish rewardPublish : rps) {
            addRP(rewardPublish);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        List<String> list = new ArrayList<>();
        list.add("个人奖惩");
        list.add("奖惩管理");
        frame.add(new RpPanel(list,"1001"));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("个人奖惩")){
            card.show(centrePanel,"1");
            setUI();
            int i=0;
            for(JButton jButton:jButtons){
                if(jButton.getText().equals("个人奖惩")){
                    break;
                }
                i++;
            }
            Style.setChangeItem(jButtons[i]);
        }
        if(e.getActionCommand().equals("管理奖惩")){
            card.show(centrePanel,"2");
            setUI();
            int i=0;
            for(JButton jButton:jButtons){
                if(jButton.getText().equals("管理奖惩")){
                    break;
                }
                i++;
            }
            Style.setChangeItem(jButtons[i]);
        }
    }
}
