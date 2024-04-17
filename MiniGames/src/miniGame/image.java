package miniGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class image {

	public void printImage() {
//		System.out.println("  ╭◜◝ ͡ ◜◝         ╭◜◝ ͡ ◜◝\r\n"
//				         + " ( •‿•。   ) ☆     ( •‿•。   ) ☆\r\n"
//				         + "  ╰◟◞ ͜ ◟◞  ╭◜◝ ͡ ◜◝╮   ͜ ◟◞╯\r\n"
//				         + "           ( •‿•。  ) ☆\r\n"
//				         + "     　     ╰◟◞ ͜ ◟◞╯\r\n"
//				         + "         음악재생중~~~~       ");
//		
		
		 try {
	            // 파일 경로 설정
	            String filePath = ".\\image\\sample.txt";
	            
	            // FileReader와 BufferedReader 생성
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            // 파일 내용 출력
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                System.out.println(line);
	            }
	            
	            // 리소스 정리
	            bufferedReader.close();
	            fileReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		
		
		
	}
	
	
	
	
}
