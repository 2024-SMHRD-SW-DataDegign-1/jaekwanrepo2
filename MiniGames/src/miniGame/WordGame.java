package miniGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordGame {

    private Random ran = new Random();
    private Scanner sc = new Scanner(System.in);
    private WordGameDAO dao = new WordGameDAO();
    private List<WordGameDTO> wordList = new ArrayList<>();
    RankDAO rank = new RankDAO();

    public WordGame() {
        // DAO를 통해 데이터베이스에서 단어 정보를 가져옴
        wordList = dao.wordData();
    }

    public void startGame(String id) {
        System.out.println("========================");
        System.out.println("      초성게임!        ");
        System.out.println("========================");
        System.out.println("처음 제시되는 초성에서 답을 맞추면 5점! ");
        System.out.println("첫번째 힌트 후 맞추면 3점!");
        System.out.println("두번째 힌트 후 맞추면 1점!");
        System.out.println("두번째 힌트 후에도 맞추지 못하면 0점입니다.");

        boolean continueGame = true;

        int totalScore = 0;
        while (continueGame) {
        	totalScore = 0;
            for (int i = 0; i < 3; i++) {
                totalScore += play();
            }

            System.out.println("수고하셨습니다. 총점수: " + totalScore);

            String input;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("게임을 계속하시겠습니까? (Y/N): ");
                input = sc.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    validInput = true;
                } else if (input.equalsIgnoreCase("N")) {
                    validInput = true;
                    continueGame = false;
                } else {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }

        System.out.println("게임을 종료합니다.");
        rank.ranksys(4,id,totalScore);
    }

    public int play() {
        int index = ran.nextInt(wordList.size());
        WordGameDTO word = wordList.get(index);
        List<String> hintList = new ArrayList<>();
        hintList.add(word.getHint1());
        hintList.add(word.getHint2());

        String chosung = word.getFirstWord(); 
        System.out.println("========================");
        System.out.println("다음 단어의 초성을 맞춰보세요: " + chosung);

        int hintCount = 0;
        boolean correctAnswer = false;
        while (hintCount < hintList.size() + 1) { 
            System.out.print("답을 입력하세요: ");
            String answer = sc.nextLine();

            if (answer.equals(word.getRight())) {
                System.out.println("정답입니다!");
                correctAnswer = true;
                break;
            } else {
                if (hintCount < hintList.size()) {
                    System.out.println("오답입니다. 힌트: \"" + hintList.get(hintCount) + "\"입니다.");
                } else {
                    System.out.println("오답입니다. 다음 문제로 넘어갑니다.");
                }
            }
            hintCount++;
        }


        wordList.remove(index); // 정답 여부와 상관없이 문제는 제거됨

        return getScore(hintCount);
    }


    private int getScore(int hintCount) {
        if (hintCount == 0) {
            return 5;
        } else if (hintCount == 1) {
            return 3;
        } else {
            return 1;
        }
    }

    private String getHint(WordGameDTO word, int hintCount) {
        if (hintCount == 0) {
            return word.getHint1();
        } else {
            return word.getHint2();
        }
    }
}

