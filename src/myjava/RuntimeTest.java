package myjava;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		Process pc = null;
		try {
			//�ܺ� ���μ��� ����
			pc = rt.exec("cmd /c dir /w");
			BufferedReader stdOut = new BufferedReader( new InputStreamReader(pc.getInputStream()) );
			String str;
			while( (str = stdOut.readLine()) != null ) {
				System.out.println(str);
			}
			System.out.println(pc);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//��ɾ� ����� ���� ���
			System.out.println(pc);
			pc.waitFor();
			System.out.println(pc);
			System.out.println("���α׷� ���� �����");
			
			//��ɾ� ����� ���� ���μ��� ����
			System.out.println(pc);
			pc.destroy();
			System.out.println("���α׷� �����");
		}
//		Process process = new ProcessBuilder("cmd", "/c", "dir", "/w").start();
//		BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
//
//		String str;
//		// ǥ����� ���¸� ���
//		while( (str = stdOut.readLine()) != null ) {
//		    System.out.println(str);
//		}
	}
}
