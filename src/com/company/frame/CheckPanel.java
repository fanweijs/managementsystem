package com.company.frame;

import com.company.dao.CheckingDAO;
import com.company.dao.EmployeeDAO;
import com.company.factory.ServiceFactory;
import com.company.model.Checking;
import com.company.model.Employee;
import com.company.service.CheckingService;
import com.company.service.EmployeeService;
import com.company.service.impl.CheckingServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by xian on 2017/12/19.
 */
public class CheckPanel extends JPanel implements ActionListener {
    private List<String> checkList = new ArrayList<>();
    private JPanel centerPanel = new JPanel();
    private CardLayout card = new CardLayout();
    private JPanel card1;
    private JPanel card2;
    private JTable table1, table2;
    private DefaultTableModel dtm, dtm2;
    private List<Checking> checkings = null;
    private List<Employee>employees = null;
    private CheckingService checkingService = ServiceFactory.getCheckingServiceInstance();
    private EmployeeService employeeService = ServiceFactory.getEmployeeServiceInstance();
    private EmployeeDAO employeeDAO = null;
    private byte[] b;
    private int[] rows;
    private int[] rows2;
    private String account;


    public CheckPanel(List<String> checkList,String account) {
        this.checkList = checkList;
        this.account= account;
        init();
    }

    public void init() {

        JButton[] jButtons = new JButton[checkList.size()];
        int i = 0;
        for (String str : checkList) {
            jButtons[i] = new JButton(str);
            jButtons[i].addActionListener(this);
            i++;
        }
        this.setLayout(new BorderLayout());

        //westPanel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(7, 1, 10, 50));
        westPanel.setBackground(Color.GRAY);
        JLabel jLabel = new JLabel();
        westPanel.add(jLabel);
        for (int c = 0; c < jButtons.length; c++) {
            westPanel.add(jButtons[c]);
        }
        add(BorderLayout.WEST, westPanel);

        centerPanel.setLayout(card);
        card1 = getCard1();
        centerPanel.add(card1, "1");
        card2 = getCard2();
        centerPanel.add(card2, "2");
        add(BorderLayout.CENTER, centerPanel);

    }

    /**
     * 个人考勤
     */
    public JPanel getCard1() {
        card1 = new JPanel();
        table1 = new JTable();
        card1.setLayout(new BorderLayout());
        card1.setBackground(Color.red);
        //northPanel
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton searchButton = new JButton("搜索");
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150, 30));
        searchButton.setBackground(Color.CYAN);
        northPanel.add(searchField);
        northPanel.add(searchButton);
        card1.add(BorderLayout.NORTH, northPanel);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = searchField.getText().trim();
                checkings = checkingService.queryLike(keywords);
                int count = dtm.getRowCount();
                for (int i = count - 1; i >= 0; i--) {
                    dtm.removeRow(i);
                }
            }
        });

        //centerPanel
        dtm = new DefaultTableModel();
        String[] titles = {"编号", "工号", "姓名", "时间", "性别"};
        //设置表头标题
        dtm.setColumnIdentifiers(titles);
        //给表格设置数据模型
        table1.setModel(dtm);
        //将单元格内容居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        //设置水平居中
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class, renderer);
        //将表头居中
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer1.setBackground(Color.LIGHT_GRAY);
        table1.getTableHeader().setDefaultRenderer(renderer1);
        //内容字符串数组
        String[] content = new String[5];
        //获取数据库中所有用户信息
        List<Checking> list = new ArrayList<>();
        list = checkingService.get(account);
        Iterator<Checking> iterator = list.iterator();
        Employee employee = employeeService.getEmployee(account);

        while (iterator.hasNext()) {
            Checking checking = iterator.next();
            content[0] = checking.getCheck_id().toString();
            content[1] = checking.getAccount();
            content[2] = employee.getName();
            content[3] = checking.getDatetime().toString();
            content[4] = checking.getCondition();
            dtm.addRow(content);
        }
        JScrollPane jsp = new JScrollPane(table1);
        card1.add(BorderLayout.CENTER,jsp);
        card1.revalidate();
        centerPanel.revalidate();
        return card1;
    }


    /**
     * 考勤管理
     */
    public JPanel getCard2() {
        JPanel card2 = new JPanel();
        card2.setLayout(new BorderLayout());
        card2.setBackground(Color.white);
        //northPanel
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton searchButton = new JButton("搜索");
        JTextField searchField = new JTextField();
        searchButton.setBackground(Color.CYAN);
        searchField.setPreferredSize(new Dimension(150, 30));
        northPanel.add(searchField);
        northPanel.add(searchButton);
        card2.add(BorderLayout.NORTH, northPanel);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kyewords = searchField.getText().trim();
                checkings = checkingService.queryLike(kyewords);
                int count = dtm.getRowCount();
                for (int i = count - 1; i >= 0; i--) {
                    dtm.removeRow(i);
                }
            }
        });

        //centerPanel
        table2 = new JTable();
        dtm2 = new DefaultTableModel();
        String[] titles = {"序号", "工号", "姓名", "职位", "考勤", "时间"};
        //设置表头标题
        dtm2.setColumnIdentifiers(titles);
        //给表格设置数据模型
        table2.setModel(dtm2);
        //将单元格内容居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        //设置水平居中
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table2.setDefaultRenderer(Object.class, renderer);
        //将表头居中
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer1.setBackground(Color.LIGHT_GRAY);
        table2.getTableHeader().setDefaultRenderer(renderer1);
        //内容字符串数组
        String[] content = new String[6];
        //获取数据库中所有用户信息
        List<Checking> list = new ArrayList<>();
//        List<Employee>list1 = new ArrayList<>();
        list = checkingService.getChecking();
//        list1 = employeeService.getAll();
        Iterator<Checking> iterator = list.iterator();
//        Iterator<Employee> iterator1 = list.iterator();

        while (iterator.hasNext()) {
            Checking checking = iterator.next();
            Employee employee = employeeService.getEmployee(checking.getAccount());
            content[0] = checking.getCheck_id().toString();
            content[1] = checking.getAccount();
            content[2] = employee.getName();
            content[3] = employee.getPosition();
            content[4] = checking.getCondition();
            content[5] = checking.getDatetime().toString();
            dtm2.addRow(content);
        }
        JScrollPane jsp2 = new JScrollPane(table2);
        jsp2.setPreferredSize(new Dimension(400, 500));

        //表格中加入考勤下拉框选项
        Vector item = new Vector();
        item.add("全勤");
        item.add("迟到");
        item.add("缺勤");
        item.add("出差");
        item.add("加班");
        JComboBox JComboBoxItem = new JComboBox(item);
        TableColumn brandColumn = table2.getColumnModel().getColumn(4);
        brandColumn.setCellEditor(new DefaultCellEditor(JComboBoxItem));

        card2.add(BorderLayout.CENTER, jsp2);
        card2.revalidate();
        centerPanel.revalidate();

        return card2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("个人考勤"))
            card.show(centerPanel, "1");
        if (e.getActionCommand().equals("管理考勤"))
            card.show(centerPanel, "2");
    }
}


