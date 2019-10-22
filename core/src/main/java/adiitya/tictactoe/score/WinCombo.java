package adiitya.tictactoe.score;

import adiitya.tictactoe.PlayerType;
import com.badlogic.gdx.utils.IntArray;

public class WinCombo {

    private IntArray indices;
    private int score = 0;

    public WinCombo(int... indices) {
        this.indices = new IntArray(indices);
    }

    public boolean containsIndex(int index) {
        return indices.contains(index);
    }

    public void tryAddScore(int score, int index) {
        if (containsIndex(index))
            this.score += score;
    }

    public boolean hasWinner() {
        return Math.abs(score) == 3;
    }

    public PlayerType getWinner() {

        if (!hasWinner())
            return PlayerType.NONE;

        return score / 3 == 1 ? PlayerType.X : PlayerType.O;
    }

    public IntArray getIndices() {
        return indices;
    }
}
