package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javazoom.jl.player.MP3Player;

public class MusicGameCon extends MP3Player {
	Scanner sc = new Scanner(System.in);
	image im = new image();
	Random ran = new Random();

	
	public void playTest(String path,int i) {
		play(path);
		timeDelay(i*5);
		stop();
	}
	
	public ArrayList<Integer> ranNum(int max) {
		ArrayList<Integer> result = new ArrayList<Integer>();	
		
		for(int i=0;i<max;i++) {
			int randomNumber = ran.nextInt(max);

			while(result.contains(randomNumber)) {
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
