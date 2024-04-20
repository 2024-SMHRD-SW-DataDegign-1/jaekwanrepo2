package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javazoom.jl.player.MP3Player;

public class MusicGameCon extends MP3Player {
	Scanner sc = new Scanner(System.in);
	image im = new image();
	Random ran = new Random();
	MusicGameDAO pathdao = new MusicGameDAO();
	RankDAO rank = new RankDAO();

	public void playTest(String id) {
		
		String intro = "음악 맞추기 게임에 오신것을 환영합니다.\r\n" 
				+ "음악을 일정시간 듣고 노래제목을 맞추는 게임입니다.\r\n" 
				+ "제목을 맞추지 못하면 듣는 시간이 5초 늘어납니다.\r\n" + "\r\n"
				+ "그럼 게임을 시작합니다!";
		slowPrint(intro, 50);
		timeDelay(2);

		ArrayList<Integer> ranSong = ranNum(pathdao.songData().size());
		int sum = 0;
		for (int j = 0; j < 3; j++) {
			int score = 60;
			for (int i = 1; i <= 3; i++) {
				im.printImage(".\\image\\music.txt");
				play(pathdao.songData().get(ranSong.get(j)).getPath());
				timeDelay(i * 5);
				stop();

				System.out.print("정답(한글로작성)" + score + "점  [0] 뒤로가기\n>>");
				String answer = sc.next();
				if (answer.equals("0")) {
					return;
				}
				else if (pathdao.songData().get(ranSong.get(j)).getName().equals(answer)) {
					System.out.println("정답입니다.");
					timeDelay(2);
					break;
				} else {
					System.out.println("오답입니다.");
					score -= 20;
					timeDelay(2);
				}
				
			}
			System.out.println("정답은 ["+pathdao.songData().get(ranSong.get(j)).getName()+"] 입니다. - "
					+pathdao.songData().get(ranSong.get(j)).getSinger());
			sum += score;
			timeDelay(2);
		}
		System.out.println("최종획득 점수는 "+sum+"입니다." );
		timeDelay(2);
		rank.ranksys(1,id,sum);
	}

	public ArrayList<Integer> ranNum(int max) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < max; i++) {
			int randomNumber = ran.nextInt(max);

			while (result.contains(randomNumber)) {
				randomNumber = ran.nextInt(max);
			}
			result.add(randomNumber);
		}

		return result;
	}

	public void timeDelay(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
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
	
	

}
