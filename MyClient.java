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
			System.out.print("입력 : ");
			//문자 입력받기
			Scanner sc = new Scanner(System.in);
			//한줄 단위로 입력받기
			String msg = sc.nextLine();

			Socket s = new Socket("localhost", 8888);
			// Socket s = new Socket("192.168.219.104", 3001);

			// 버퍼에 문자 입력하면 실행됨
			if (msg != null && msg.trim().length() > 0) {

				// Socket s = null;

				try {
					// 접속할 서버의 ip와 port를 통해 연결되는 클라이언트
					// s = new Socket("125.143.191.54", 3001);

					// 위에서 입력받은 문자열을 서버로 전달하기 위해 스트림 준비
					//송신==Sender
					PrintWriter out = new PrintWriter(s.getOutputStream());

					// 서버로 문자열 전송
					out.write(msg);
					out.flush();
					out.close();

					// ---------------------- 수신 ------------------------
					BufferedReader reader = null;
					InputStreamReader isr = null;

					isr = new InputStreamReader(s.getInputStream());
					reader = new BufferedReader(isr);

					String rmsg = reader.readLine();
					String ip = s.getInetAddress().getHostAddress(); // IP 주소를 반환

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