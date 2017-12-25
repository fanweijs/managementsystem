package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.model.Salary;
import com.company.service.FinancialAdminService;
import com.company.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SalaryPanel extends JPanel implements ActionListener {
    private List<String>moneyList = new ArrayList<>();
    private String account;
    private JPanel westPanel;
    private JPanel centerPanel;
    private CardLayout card = new CardLayout();
    private JPanel card1 = new JPanel();
    private JTable personTabel = new JTable();
    private DefaultTableModel dtm ;
    private DefaultTableModel dtm1;
    private JPanel card2 = new JPanel();
    private UserService userService = ServiceFactory.getUserSerivceInstance();
    private FinancialAdminService financialAdminService = ServiceFactory.getFinanicalAdminInstance();

    public SalaryPanel(List<String>moneyList,String account){
        this.moneyList= moneyList;
        this.account=account;
        init();
    }

    public void init(){
        centerPanel = new JPanel();
        this.setLayout(new BorderLayout());
        JButton[] jButtons = new JButton[moneyList.size()];
        westPanel = new JPanel(new GridLayout(7,1));
        int i = 0;
        for (String str : moneyList) {
            jButtons[i] = new JButton(str);
            jButtons[i].addActionListener(this);
            westPanel.add(jButtons[i]);
            i++;
        }
        add(westPanel,BorderLayout.WEST);
        centerPanel.setLayout(card);
        card1 = getCard1();
        centerPanel.add(card1,"1");
        card2 = getCard2();
        centerPanel.add(card2,"2");
        add(BorderLayout.CENTER,centerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("个人财务"))
            card.show(centerPanel, "1");
        if (e.getActionCommand().equals("管理财务"))
            card.show(centerPanel, "2");


    }
    public JPanel getCard1() {
        dtm = new DefaultTableModel();
        String[]titles  ={"工号","姓名","日期","职位等级","基本工资","等级工资","全勤奖","补贴",
                "应发工资","各项减款","个人保险","税收","实发工资","是否发放"};
        dtm.setColumnIdentifiers(titles);
        personTabel.setModel(dtm);
        // 将单元格内容居中
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        // 设置水平方向居中
        r.setHorizontalAlignment(JLabel.CENTER);
        personTabel.setDefaultRenderer(Object.class, r);
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        personTabel.getTableHeader().setDefaultRenderer(r1);
        String[] content = new String[14];
        List<Salary>list = new ArrayList<>();
        list = userService.getPersonSalary(account);
        //获取到数据库中所有用户信息
//        students = adminService.getAll();
        addAllRow(dtm,list);
        JScrollPane jScrollPane = new JScrollPane(personTabel);
       jScrollPane.setPreferredSize(new Dimension(400,500));
        card1.setLayout(new BorderLayout());
        Dimension dimension = card1.getSize();
        card1.add(jScrollPane);
        card1.revalidate();
        centerPanel.revalidate();
        return card1;
    }
    public JPanel getCard2() {

        FinancialAdminService financialAdminService =ServiceFactory.getFinanicalAdminInstance();
        //north
        card2.setLayout(new BorderLayout());
        JPanel card2NorthPanel  = new JPanel();
        card2NorthPanel.setPreferredSize(new Dimension(card2.getWidth(),120));
        card2NorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER,500,60));
        JButton[]jButtons =new JButton[8] ;
        jButtons[0]=new JButton("发放本月工资");
        JTextField jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(200,40));
        jButtons[1] =new JButton("搜索");
        card2NorthPanel.add(jButtons[0]);
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        jPanel.add(jTextField);
        jPanel.add(jButtons[1]);
        card2NorthPanel.add(jPanel);
        card2.add(BorderLayout.NORTH,card2NorthPanel);
        //center 表格
        JTable jTable = new JTable();
        jTable.setEnabled(false);
        dtm1 = new DefaultTableModel();
        String[]titles  ={"工号","姓名","日期","职位等级","基本工资","等级工资","全勤奖","补贴",
                "应发工资","各项减款","个人保险","税收","实发工资","是否发放"};
        dtm1.setColumnIdentifiers(titles);
        jTable.setModel(dtm1);
        // 将单元格内容居中
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        // 设置水平方向居中
        r.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, r);
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
       jTable.getTableHeader().setDefaultRenderer(r1);
        String[] content = new String[14];
        List<Salary>list1 = new ArrayList<>();
        list1 = financialAdminService.getAllSalary();
        addAllRow(dtm1,list1);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(400,500));
        jButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = jTextField.getText().trim();
                List<Salary>list = new ArrayList<>();
                list= financialAdminService.qureyLikeSalary(keywords);
                int count = dtm1.getRowCount();
                for (int i = count - 1; i >= 0; i--) {
                    dtm1.removeRow(i);
                }
                for ( Salary salary : list) {
                    addSalaryRow(salary);
                }
            }
        });
        jButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("发放本月工资")){
                  jButtons[0].setText("结束发放");
                  dtm1.setRowCount(0);
                List<Salary>salaryList =  financialAdminService.PayOff();
//                  addAllRow(dtm1,salaryList);
                  for(Salary salary:salaryList){
                      addSalaryRow(salary);
                  }
                }
                if (e.getActionCommand().equals("结束发放")){
                    jButtons[0].setText("发放本月工资");
                    dtm1.setRowCount(0);
                     List<Salary> list1 = financialAdminService.getAllSalary();
                     addAllRow(dtm1,list1);
                }
            }
        });
       jTable.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               if(e.getClickCount()==2){
                   System.out.println("fanwei");
               }
           }
       });

        card2.add(BorderLayout.CENTER,jScrollPane);
        card2.revalidate();
        centerPanel.revalidate();
        //south
        JPanel card2SouthPanel =  new JPanel();
        card2.add(BorderLayout.SOUTH,card2SouthPanel);
        return card2;
    }

    public void addSalaryRow(Salary salary){
        Employee employee =userService.getEmployee(salary.getAccount());
        Object[] rowData = {salary.getAccount(),employee.getName(),salary.getSdate(),salary.getPoistion_level(),
                salary.getBaseSalary(),salary.getLevelSalary(), salary.getAllChecking(),salary.getSubsidy(),
                salary.getsSalary(),salary.getLeaveCut(),salary.getSelfInsurance(),salary.getTax(),
                salary.gettSalary(),salary.getFlag()};
        dtm1.addRow(rowData);
    }

    public void  addAllRow(DefaultTableModel dtm, List<Salary>list){
        String[]content =  new String[14];
        Iterator<Salary> iterator = list.iterator();
        while (iterator.hasNext()) {

            Salary salary = iterator.next();
            Employee employee = userService.getEmployee(salary.getAccount());
            content[0] = salary.getAccount();
            content[1] =employee.getName();
            content[2]= salary.getSdate().toString();
            content[3] = String .valueOf(salary.getPoistion_level());
            content[4] = String.valueOf(salary.getBaseSalary());
            content[5]= String.valueOf(salary.getLevelSalary());
            content[6] = String.valueOf(salary.getAllChecking());
            content[7] = String.valueOf(salary.getSubsidy());
            content[8] =String.valueOf( salary.getsSalary());
            content[9] = String.valueOf(salary.getLeaveCut());
            content[10] =String.valueOf(salary.getSelfInsurance());
            content[11]= String.valueOf(salary.getTax());
            content[12]=String.valueOf(salary.gettSalary());
            content[13]=salary.getFlag();
            dtm.addRow(content);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        List<String> list = new ArrayList<>();
        list.add("个人薪资");
        list.add("管理财务");
        frame.add(new SalaryPanel(list,"1001"));
        frame.setVisible(true);
    }


}
