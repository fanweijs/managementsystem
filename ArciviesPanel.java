package com.company.frame;

import com.company.Factory.ServiceFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 高展 on 2017/12/21.
 * @author 高展
 */
public class ArciviesPanel extends JPanel implements ActionListener{
    private List<String> arciviesList=new ArrayList<>();
    private CardLayout card=new CardLayout();
    private GridLayout wg=new GridLayout();
    private JPanel centerPanel=new JPanel();
    private DefaultTableModel dtm;
    private JTable table;
    private JPanel card2;
    private JPanel card1;
    private JPanel card3;
    private String account;
    private java.util.List<Employee>employees=new ArrayList<>();
    private EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();

    public ArciviesPanel(List<String>arciviesList,String account)  {
        this.account=account;
        this.arciviesList=arciviesList;
        init();
    }

    private void init(){
        JButton[]jButtons=new JButton[arciviesList.size()];
        int i=0;
        for (String str: arciviesList){
            jButtons[i]=new JButton(str);
            jButtons[i].addActionListener(this);
            i++;
        }
        this.setLayout(new BorderLayout());
        JPanel westPanel=new JPanel();
        westPanel.setLayout(new GridLayout(7,1,0,50));
        JLabel jLabel=new JLabel();
        westPanel.add(jLabel);
        for (int c=0;c<jButtons.length;c++){
            westPanel.add(jButtons[c]);
        }
        add(BorderLayout.WEST,westPanel);
        centerPanel.setLayout(card);

        card1=getCard1();
        card2=getCard2();
        card3=getCard3();
        centerPanel.add(card1,"1");
        centerPanel.add(card2,"2");
        centerPanel.add(card3,"3");
        add(BorderLayout.CENTER,centerPanel);

    }
    public JPanel getCard1(){
        card1 = new JPanel();
        card1.setBackground(Color.gray);
        card1.setLayout(new BorderLayout());
        //north 头像
        JPanel northPanel=new JPanel();
        northPanel.setPreferredSize(new Dimension(card1.getWidth(),100));
        JLabel avatarLabel=new JLabel();
        //avatarLabel.setIcon(new ImageIcon());
        card1.add(BorderLayout.NORTH,northPanel);
        // south 网格布局
        JPanel southPanel=new JPanel();
        southPanel.setLayout(new GridLayout(13,1));


        return  card1;
    }
    public JPanel getCard2()  {
        card2=new JPanel();
        card2.setLayout(new BorderLayout());
        card2.setBackground(Color.red);
        //north
        JPanel northPanel =new JPanel();
        northPanel.setPreferredSize(new Dimension(card2.getWidth(),100));
        northPanel.setLayout(new FlowLayout());
        JTextField searchField=new JTextField();
        searchField.setPreferredSize(new Dimension(200,40));
        JButton searchButton=new JButton("搜索");
        searchButton.setPreferredSize(new Dimension(120,40));
        northPanel.add(searchField);
        northPanel.add(searchButton);
        card2.add(BorderLayout.NORTH,northPanel);
        // center 表格
        table=new JTable();
        dtm=new DefaultTableModel();
        String[] titles={"工号","部门","姓名","性别","出生日期","职务","婚姻状况","政治面貌","学历","联系方式","省份","城市","入职时间"};
        //设置表头标题
        dtm.setColumnIdentifiers(titles);
        //给表格设置数据模型
        table.setModel(dtm);
        //将单元格内容居中
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        //设置水平居中
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        //将表头居中
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer1.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        //内容字符串数组
        String[] content=new String[14];
        //获取数据库中所有用户信息
        employees=employeeService.getAll();
        Iterator<Employee>iterator=employees.iterator();
        while (iterator.hasNext()){
            Employee employee=iterator.next();
            content[0]=employee.getAccount();
            String dept = employee.getDepartmentID() ==1 ?"人事部":"财务部";
            content[1]=dept;
            content[2]=employee.getName();
            content[3]=employee.getSex();
            content[4]=String.valueOf(employee.getBirthday());
            content[5]=employee.getPosition();
            content[6]=employee.getMaritalstatus();
            content[7]=employee.getPolitiacal_status();
            content[8]=employee.getEducation();
            content[9]=employee.getPhone();
            content[10]=employee.getNative_province();
            content[11]=employee.getNative_city();
            content[12]=employee.getDate().toString();
            dtm.addRow(content);
        }
        JScrollPane jsp=new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(400,500));
        card2.add(BorderLayout.CENTER,jsp);
        card2.revalidate();
        centerPanel.revalidate();
        //south
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,40,40));
        JButton[]jButtons = new JButton[2];
        jButtons[0] = new JButton("修改");
        jButtons[1]=new JButton("删除");
        southPanel.add(jButtons[0]);
        southPanel.add(jButtons[1]);
        for(int i=0;i<2;i++){
            jButtons[i].setPreferredSize(new Dimension(120,40));
        }
        southPanel.setPreferredSize(new Dimension(card2.getWidth(),150));
        card2.add(BorderLayout.SOUTH,southPanel);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords=searchField.getText();
                try{
                    employees=employeeService
                }
            }
        });
        return card2;
    }
    public JPanel getCard3(){
        card3 =new JPanel();
        card3.setBackground(Color.blue);
        return  card3;
    }


    public static void main(String[] args) throws SQLException {
        JFrame frame=new JFrame("档案管理");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        List<String>list=new ArrayList<>();
        list.add("个人档案");
        list.add("所有员工档案");
        list.add("添加员工档案");
        frame.add(new ArciviesPanel(list,"1001"));
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("个人档案")){
            card.show(centerPanel,"1");
        }
        if (e.getActionCommand().equals("所有员工档案")){
            card.show(centerPanel,"2");
        }
        if (e.getActionCommand().equals("添加员工档案")){
            card.show(centerPanel,"3");
        }
    }
}
