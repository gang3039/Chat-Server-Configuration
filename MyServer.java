import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends Thread {

	ServerSocket ss;

	String[] ipZip = new String[5];
	int index = 0;

	public MyServer() {
		try {
			ss = new ServerSocket(8888);//��Ʈ��ȣ �Ѱ��ֱ�
			

		} catch (Exception e) {
			// TODO: handle exception
			
			//���� �޼��� ���
			System.out.println(e.getMessage());
		}
	}

	@Override
	//���� ����
	public void run() {
		while (true) {
			try {
				//������ ���� �غ� �Ǿ��� �� 
				//������ ���� ���� ���� �ٽ� ����
				Socket s = ss.accept();
                System.out.println("ä�� ����");
				
				
				// Ŭ���̾�Ʈ�� �������ڸ��� ���ڿ��� ������ ������
				// �ٷ� ���ڿ��� �޾��ش�.
				//Reader-->����(Receiver)
				BufferedReader reader = null;
				InputStreamReader isr = null;

				isr = new InputStreamReader(s.getInputStream());
				reader = new BufferedReader(isr);

				String msg = reader.readLine();
				String ip = s.getInetAddress().getHostAddress(); // IP �ּҸ� ��ȯ

				boolean isThere = false;

				// ��ġ�ϴ°� �ִ��� ������ Ȯ��
				for (int i = 0; i < ipZip.length; i++) {
					if (ipZip[i] == ip) {
						isThere = true;
						break;
					}
				}

				// ���ο� ip�� �迭�� ��������
				if (!isThere) {
					ipZip[index++] = ip;
				}

				System.out.println(ip + " :: " + msg);

				// ���� ���ڿ��� �����غ���
				/*
				 * BufferedReader sb = new BufferedReader(new InputStreamReader(System.in));
				 * String smsg = sb.readLine();
				 */

				PrintWriter sw = new PrintWriter(s.getOutputStream());

				sw.println(msg); // sw.print(smsg);
				sw.flush();//���� ����ֱ�

				reader.close();
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // while

	}
}