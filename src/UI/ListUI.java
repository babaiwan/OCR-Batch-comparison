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
         
    	
    	JFrame jf = new JFrame("���Դ���");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        
        JButton bt1=new JButton("�Ա�");
        panel.add(bt1);
    
        final JList<String> list = new JList<String>();  // ����һ�� JList ʵ��     
        list.setPreferredSize(new Dimension(200, 100));// ����һ����ѡ��С
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // ����ɼ�ϵĶ�ѡ

        String[] FinalString = null; 
        int size=ListAnswer.size();
        FinalString=(String[])ListAnswer.toArray(new String[size]);
        list.setListData(FinalString);

        // ���ѡ��ѡ��״̬���ı�ļ�����
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting())
            {    
            	index = list.getSelectedIndex();    // ��ȡ��ѡ�е�ѡ������
                ListModel<String> listModel = list.getModel();   // ��ȡѡ�����ݵ� ListModel
                System.out.println("ѡ��: " + index + " = " + listModel.getElementAt(index));   
//                Store store =new Store(index,a,b);
              }
            }
        });
     
        bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			String command=e.getActionCommand();		
					try {											//��ȡture��false��������ţ���filepath�����������һ���Աȿ�
						
//					    System.out.println(index);
						new Windowm(a,b,index);
						}catch(Exception e1){
				}
			}
		});    
        
        // ����Ĭ��ѡ����
        list.setSelectedIndex(1);

        // ��ӵ������������
        panel.add(list);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}