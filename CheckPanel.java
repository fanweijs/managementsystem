package com.company.frame;

import com.company.dao.CheckingDAO;
import com.company.factory.ServiceFactory;
import com.company.model.Checking;
import com.company.service.CheckingService;
import com.company.service.impl.CheckingServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xian on 2017/12/19.
 */
public class CheckPanel extends JPanel implements ActionListener {
    private List<String> checkList = new ArrayList<>();
    private JPanel centerPanel = new JPanel();
    private CardLayout card = new CardLayout();
    private JPanel card1;
    private JPanel card2;
    private JTable table1,table2;
    private DefaultTableModel dtm,dtm2;
    private DefaultTableCellRenderer renderer,renderer1;
  //  private Iterator<Checking> iterator = null;
    private Checking checking = null;
    private CheckingService checkingService = ServiceFactory.getCheckingServiceInstance();
    private byte[] b;
    private int[] rows;
    private int[] rows2;


    public CheckPanel(List<String> checkList) {
        this.checkList = checkList;
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
        card1.setBackground(Color.CYAN);
        //northPanel
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton searchButton = new JButton("搜索");
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150, 30));
        searchButton.setBackground(Color.CYAN);
        northPanel.add(searchField);
        northPanel.add(searchButton);

        card1.add(BorderLayout.NORTH, northPanel);

        //centerPanel
        dtm=new DefaultTableModel();
        String[] titles={"编号","工号","时间","性别"};
        //设置表头标题
        dtm.setColumnIdentifiers(titles);
        //给表格设置数据模型
        table1.setModel(dtm);
        //将单元格内容居中
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        //设置水平居中
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class,renderer);
        //将表头居中
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer1.setBackground(Color.LIGHT_GRAY);
        table1.getTableHeader().setDefaultRenderer(renderer1);
        //内容字符串数组
        String[] content=new String[14];
        //获取数据库中所有用户信息
//        checking= CheckingService.
        List<Checking>list = new ArrayList<>();
        list= checkingService.get("1001");
        Iterator<Checking>iterator=list.iterator();
        while (iterator.hasNext()){
            Checking checking=iterator.next();
            content[0]=checking.getCheck_id().toString();
            content[1]=checking.getAccount();
            content[2]=checking.getDatetime().toString();
            content[3]=checking.getCondition();
            dtm.addRow(content);
        }
        JScrollPane jsp=new JScrollPane(table1);
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
        card2.setBackground(Color.orange);




        return card2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        List<String> list = new ArrayList<>();
        list.add("个人考勤");
        list.add("考勤管理");
        frame.add(new CheckPanel(list));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("个人考勤"))
            card.show(centerPanel, "1");
        if (e.getActionCommand().equals("考勤管理"))
            card.show(centerPanel, "2");
    }
}


