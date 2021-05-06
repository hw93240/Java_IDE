package hello;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class Eclipse_Compiler extends JFrame {

	private JPanel contentPane;
	private JTextArea inputArea;
	private JTextArea outputArea;
	
	private String inputData = "";				// �Էµ� ���ڸ� �����ϴ� �ӽ� ���ڿ� ������
	private StringBuffer savingData;   			
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eclipse_Compiler frame = new Eclipse_Compiler();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Eclipse_Compiler() {
		setTitle("Eclipse Compiler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f = new JFrame();
				f.setSize(350, 250);
				
				TextArea t = new TextArea();
				f.add(t);
				
				f.setVisible(true);
				
				FileDialog dialog = new FileDialog(f, "Open", FileDialog.LOAD);
				dialog.setVisible(true);
				
				String path = dialog.getDirectory() + dialog.getFile();
				
				String s = "";
				
				try {
					
					FileReader r = new FileReader( path);
					
					int k;
					for( ; ;) {
						
						k = r.read();
						if( k == -1) break;
						s += (char) k;
					}
					
					r.close();
				}catch(Exception e2) {
					
					System.out.println("����"+e);
				}
				
				t.setText(s);
			}
		});
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f = new JFrame();
				f.setSize(350, 250);
				
				TextArea t = new TextArea();
				f.getContentPane().add(t);
				
				f.getContentPane().setLayout( null );
				
				f.setVisible(true);
				
				FileDialog dialog = new FileDialog(f, "Save", FileDialog.SAVE);
				dialog.setVisible(true);
				
				String path = dialog.getDirectory() + dialog.getFile();
				System.out.println(path);
				
				try {
					
					FileWriter w = new FileWriter(path);
					String s = t.getText();
					w.write( s );
					w.close();
				} catch(Exception e2) {
					
					System.out.println("�������"+e2);
					
				}
						
						
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Copy");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputArea.copy();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Paste");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputArea.paste();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cut");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputArea.cut();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("Compile");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Compile");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String className = getClassName();
				String cmd = new String("javac " +  className + ".java");
				
				try {
					Process pc = Runtime.getRuntime().exec(cmd);
					outputArea.setText("������ �Ϸ�");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					outputArea.setText("������ ����");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("run");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String className = getClassName();
				String cmd = new String("java " +  className);
				
				try {
					Process pc = Runtime.getRuntime().exec(cmd);
					BufferedReader stdOut = new BufferedReader( new InputStreamReader(pc.getInputStream()) );
					String str;
					while( (str = stdOut.readLine()) != null ) {
						outputArea.setText(str);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					outputArea.setText("���� ����");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		inputArea = new JTextArea();
		scrollPane.setViewportView(inputArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		outputArea = new JTextArea();
		scrollPane_1.setViewportView(outputArea);
	}
	
	// ���� �Էµ� �ڵ��� Ŭ���� �̸��� ������
	public String getClassName() {
		String className = inputArea.getText();
		String[] strArray = className.split(" ");
		for (int i = 0; i < strArray.length; i++)
		{
			if (strArray[i].equals("class")) {
				className = strArray[i + 1];
				break;
			}
		}
		return className;
	}
}
