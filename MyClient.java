import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
	public static void main(String[] args) throws IOException {
		boolean quit = true;

		while (quit) {
			System.out.print("�Է� : ");
			//���� �Է¹ޱ�
			Scanner sc = new Scanner(System.in);
			//���� ������ �Է¹ޱ�
			String msg = sc.nextLine();

			Socket s = new Socket("localhost", 8888);
			// Socket s = new Socket("192.168.219.104", 3001);

			// ���ۿ� ���� �Է��ϸ� �����
			if (msg != null && msg.trim().length() > 0) {

				// Socket s = null;

				try {
					// ������ ������ ip�� port�� ���� ����Ǵ� Ŭ���̾�Ʈ
					// s = new Socket("125.143.191.54", 3001);

					// ������ �Է¹��� ���ڿ��� ������ �����ϱ� ���� ��Ʈ�� �غ�
					//�۽�==Sender
					PrintWriter out = new PrintWriter(s.getOutputStream());

					// ������ ���ڿ� ����
					out.write(msg);
					out.flush();
					out.close();

					// ---------------------- ���� ------------------------
					BufferedReader reader = null;
					InputStreamReader isr = null;

					isr = new InputStreamReader(s.getInputStream());
					reader = new BufferedReader(isr);

					String rmsg = reader.readLine();
					String ip = s.getInetAddress().getHostAddress(); // IP �ּҸ� ��ȯ

					System.out.println(ip + " :: " + rmsg);

				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					s.close();
					if (msg.equals("quit")) {
						quit = false;
					}
				}

			}

		}
	}
}