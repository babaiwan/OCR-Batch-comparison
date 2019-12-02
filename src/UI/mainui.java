package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class mainui extends JFrame{
	JPanel mypanel=new JPanel();
	
	JButton bt1=new JButton("��Ӳ�ɨ��");
	JButton bt2=new JButton("��ӱȶ�·��");
	JButton bt3=new JButton("�Ա�");
	
	static File SourceFile;

	File ComparisonFile;
	JSplitPane jSplitPane=new JSplitPane();
	
	
	public mainui(){
		setVisible(true);
		mypanel.add(bt1);
		mypanel.add(bt2);
		mypanel.add(bt3);
		
		
		this.setTitle("Hello world");
		this.setBounds(100, 100, 600, 500);
		setContentPane(mypanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bt1.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent e4)
				{	
					try {
						JFileChooser jfc=new JFileChooser();
						jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						jfc.showDialog(new JLabel(),"ѡ����Դ·��");
						
						SourceFile=jfc.getSelectedFile();	
						
//						System.out.println(SourceFile);
						
						if(SourceFile != null) {
							String s=SourceFile.toString();
							cmd(s);
						}
						
						}catch(Exception e) {
						}
					
			
				}
		   }
    	);
		
		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					JFileChooser jfc=new JFileChooser();
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.showDialog(new Label(),"ѡ��Ա�·��");
					
					ComparisonFile=jfc.getSelectedFile();
//					System.out.println(Comparisonfolder);
					
				}catch(Exception e1){
				}
			}
			
		});
		
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
//				System.out.println(SourceFile);
//				System.out.println(ComparisonFile);
				if(SourceFile !=null && ComparisonFile != null)
				{		
					try {
					compare(SourceFile,ComparisonFile);		//������
					}
					catch(Exception e){
					e.printStackTrace();
					}
				}
				else
					System.out.println("δѡ����·��");
				
			}
			}			
		);
		
		
	}
	
	public static void cmd(String fileDir) { 	
		String comd = null;
		
		File file=new File(fileDir);	//�����ļ�λ�õ�file������ͨ��listFile��ȡ��Ŀ¼�����е�file��files������ȥ
		File[]files=file.listFiles();
		if(files==null)
			return;
		
		
		try
		{	
			for(File f:files)
			{
			String name=f.getName();
			String lastname=name.substring(name.lastIndexOf(".")+1);
			String fname = null;
			
			if(lastname.equals("jpg")||lastname.equals("JPG"))
				{	
					fname=name.substring(0,name.lastIndexOf(".")).toString();
					comd = "tesseract "+name+" "+fname;
					Runtime.getRuntime().exec(comd,null,new File(SourceFile.toString()));
				
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void compare(File a,File b) {		//���ݴ�����һ�Ƚ�	
//		System.out.print(a);
//		System.out.print(b);
		File[] SFolder=a.listFiles();
		File[] CFolder=b.listFiles();
		ArrayList<Object> List=new ArrayList<Object>();						//��űȽϽ��
		ArrayList<String> Filename=new ArrayList<String>();					//���κ���
		
		try {						//��ȡtxt�ļ�
			int i=0;int j=0;
			while(SFolder[i]!=null) {
				String name=SFolder[i].getName();
				String lastname=name.substring(name.lastIndexOf(".")+1);
		
				if(lastname.equals("txt") )
				{
					Filename.add(name);
					List.add(compareFile(SFolder[i],CFolder[j]));	
					j++;
				}
				i++;
			}
		}
		catch(Exception e){
//			e.printStackTrace();
		}
		for(int i=0;i<List.size();i++)
			{
				String aleach= (String)List.get(i);
				System.out.println(aleach);				//�����
				
				String name=(String)Filename.get(i);
				System.out.println(name);
			}
													
		ListUI.OutPutList(List,Filename,a,b);
		
	}
	
	@SuppressWarnings("resource")
	public static String compareFile(File a,File b){	//���бȽ�
//		System.out.println(a+"\n");
//		System.out.println(b+"\n");	
		try {
			FileReader fd1=new FileReader(a);
			FileReader fd2=new FileReader(b);
			BufferedReader bfr2=new BufferedReader(fd2);
			BufferedReader bfr1=new BufferedReader(fd1);
			String line1=null;String line2=null;
			
			while((line1=bfr1.readLine())!=null &&(line2=bfr2.readLine())!=null){
//			System.out.println(line1);
//			System.out.println(line2);
//			System.out.println("\n"+"---------------"+"\n");
			if(line1.equals(line2)!=true)
			{	
				bfr1.close();
				bfr2.close();
				//��¼falseʱ����ļ����
				return "false";
			}
				
//				JButton button=new JButton();
//				mypanel.add(button);
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "true";
		
	}
	
	
}
