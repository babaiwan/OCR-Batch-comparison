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
	
	JButton bt1=new JButton("添加并扫描");
	JButton bt2=new JButton("添加比对路径");
	JButton bt3=new JButton("对比");
	
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
						jfc.showDialog(new JLabel(),"选择来源路径");
						
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
					jfc.showDialog(new Label(),"选择对比路径");
					
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
					compare(SourceFile,ComparisonFile);		//主代码
					}
					catch(Exception e){
					e.printStackTrace();
					}
				}
				else
					System.out.println("未选择完路径");
				
			}
			}			
		);
		
		
	}
	
	public static void cmd(String fileDir) { 	
		String comd = null;
		
		File file=new File(fileDir);	//传入文件位置到file变量，通过listFile获取子目录下所有的file到files数组中去
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
	
	public static void compare(File a,File b) {		//数据处理逐一比较	
//		System.out.print(a);
//		System.out.print(b);
		File[] SFolder=a.listFiles();
		File[] CFolder=b.listFiles();
		ArrayList<Object> List=new ArrayList<Object>();						//存放比较结果
		ArrayList<String> Filename=new ArrayList<String>();					//传参函数
		
		try {						//提取txt文件
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
				System.out.println(aleach);				//主输出
				
				String name=(String)Filename.get(i);
				System.out.println(name);
			}
													
		ListUI.OutPutList(List,Filename,a,b);
		
	}
	
	@SuppressWarnings("resource")
	public static String compareFile(File a,File b){	//逐行比较
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
				//记录false时候的文件编号
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
