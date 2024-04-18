package miniGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Function {

	public void slowPrint(String text, int delay) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(delay); // 지정한 시간만큼 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); // 마지막에 개행 출력
    }

	public void loadQuestionTxt(String filePath) { // 문제 txt파일 출력용
		try {
			// 파일 경로 설정 filePath

			// FileReader와 BufferedReader 생성
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// 파일 내용 출력
			String line;
			int i = 0 ;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.equals("*")) {
					break;
				}
				System.out.println(++i +"   " + line); //행 번호 출력 i
				
			}
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line); //행 번호 출력 i
				
			}

			// 리소스 정리
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			}
		}
		
		public void loadTxt(String filePath) { // 일반 txt파일 출력용
			try {
				// 파일 경로 설정 filePath
				
				// FileReader와 BufferedReader 생성
				FileReader fileReader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				// 파일 내용 출력
				String line;
				int i = 0 ;
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line); //행 번호 출력 i
				}
				
				// 리소스 정리
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
