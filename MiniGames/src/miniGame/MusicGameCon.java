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

		ArrayList<Integer> ranSong = ranNum(pathdao.songData().size());
		int sum = 0;
		for (int j = 0; j < 5; j++) {
			int score = 60;
			for (int i = 1; i <= 3; i++) {
				im.printImage(".\\image\\music.txt");
				play(pathdao.songData().get(ranSong.get(j)).getPath());
				timeDelay(i * 5);
				stop();

				System.out.print("정답(한글로작성)" + score + "점  [0] 게임종료 ");
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
			sum += score;
			System.out.println(sum);
		}
		
		
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
	
	
	
	

}
