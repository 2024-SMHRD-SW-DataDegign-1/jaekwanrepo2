package miniGame;

public class WordGameDTO {

    private String right;
    private String firstWord;
    private String Hint1;
    private String Hint2;

    public WordGameDTO(String right, String firstWord, String hint1, String hint2) {
        this.right = right;
        this.firstWord = firstWord;
        Hint1 = hint1;
        Hint2 = hint2;
    }

    public String getRight() {
        return right;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public String getHint1() {
        return Hint1;
    }

    public String getHint2() {
        return Hint2;
    }
}
