package miniGame;

public class RankDTO {
	
	private String id;
	private int musicScore;
	private int codeScore;
	private int imageScore;
	private int wordScore;
	
	
	public RankDTO(String id) {
		this.id = id;
	}
	
	public RankDTO(String id, int musicScore, int codeScore, int imageScore, int wordScore) {
		this.id = id;
		this.musicScore = musicScore;
		this.codeScore = codeScore;
		this.imageScore = imageScore;
		this.wordScore = wordScore;
	}




	public int getMusicScore() {
		return musicScore;
	}


	public void setMusicScore(int musicScore) {
		this.musicScore = musicScore;
	}


	public int getCodeScore() {
		return codeScore;
	}
	
	
	public void setCodeScore(int codeScore) {
		this.codeScore = codeScore;
	}
	
	public int getImageScore() {
		return imageScore;
	}


	public void setImageScore(int imageScore) {
		this.imageScore = imageScore;
	}



	public int getWordScore() {
		return wordScore;
	}


	public void setWordScore(int wordScore) {
		this.wordScore = wordScore;
	}


	public String getId() {
		return id;
	}


	
	
	
	
	
	
	

}
