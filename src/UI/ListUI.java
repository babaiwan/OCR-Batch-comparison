package UI;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

public class ListUI {
		static int index;
public static void OutPutList(ArrayList<Object> ListAnswer,File a,File b) {
         
    	
    	JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        
        JButton bt1=new JButton("对比");
        panel.add(bt1);
    
        final JList<String> list = new JList<String>();  // 创建一个 JList 实例     
        list.setPreferredSize(new Dimension(200, 100));// 设置一下首选大小
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // 允许可间断的多选

        String[] FinalString = null; 
        int size=ListAnswer.size();
        FinalString=(String[])ListAnswer.toArray(new String[size]);
        list.setListData(FinalString);

        // 添加选项选中状态被改变的监听器
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting())
            {    
            	index = list.getSelectedIndex();    // 获取被选中的选项索引
                ListModel<String> listModel = list.getModel();   // 获取选项数据的 ListModel
                System.out.println("选中: " + index + " = " + listModel.getElementAt(index));   
//                Store store =new Store(index,a,b);
              }
            }
        });
     
        bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			String command=e.getActionCommand();		
					try {											//获取ture和false的索引序号，把filepath传过来，输出一个对比框
						
//					    System.out.println(index);
						new Windowm(a,b,index);
						}catch(Exception e1){
				}
			}
		});    
        
        // 设置默认选中项
        list.setSelectedIndex(1);

        // 添加到内容面板容器
        panel.add(list);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}