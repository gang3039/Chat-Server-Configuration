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
			ss = new ServerSocket(8888);//포트번호 넘겨주기
			

		} catch (Exception e) {
			// TODO: handle exception
			
			//오류 메세지 출력
			System.out.println(e.getMessage());
		}
	}

	@Override
	//서버 실행
	public void run() {
		while (true) {
			try {
				//서버가 연결 준비가 되었을 때 
				//데이터 연결 전용 소켓 다시 생성
				Socket s = ss.accept();
                System.out.println("채팅 시작");
				
				
				// 클라이언트는 접속하자마자 문자열을 보내기 때문에
				// 바로 문자열을 받아준다.
				//Reader-->수신(Receiver)
				BufferedReader reader = null;
				InputStreamReader isr = null;

				isr = new InputStreamReader(s.getInputStream());
				reader = new BufferedReader(isr);

				String msg = reader.readLine();
				String ip = s.getInetAddress().getHostAddress(); // IP 주소를 반환

				boolean isThere = false;

				// 일치하는게 있는지 없는지 확인
				for (int i = 0; i < ipZip.length; i++) {
					if (ipZip[i] == ip) {
						isThere = true;
						break;
					}
				}

				// 새로운 ip면 배열에 저장해줌
				if (!isThere) {
					ipZip[index++] = ip;
				}

				System.out.println(ip + " :: " + msg);

				// 받은 문자열을 전송해보자
				/*
				 * BufferedReader sb = new BufferedReader(new InputStreamReader(System.in));
				 * String smsg = sb.readLine();
				 */

				PrintWriter sw = new PrintWriter(s.getOutputStream());

				sw.println(msg); // sw.print(smsg);
				sw.flush();//버퍼 비워주기

				reader.close();
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // while

	}
}