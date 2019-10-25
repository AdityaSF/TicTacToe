package adiitya.tictactoe.score;

import adiitya.tictactoe.PlayerType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

import java.util.stream.StreamSupport;

public class ScoreManager {

    private Array<WinCombo> combos = new Array<>();

    public ScoreManager() {
        combos.add(new WinCombo(0, 1, 2));
        combos.add(new WinCombo(3, 4, 5));
        combos.add(new WinCombo(6, 7, 8));
        combos.add(new WinCombo(0, 3, 6));
        combos.add(new WinCombo(1, 4, 7));
        combos.add(new WinCombo(2, 5, 8));
        combos.add(new WinCombo(0, 4, 8));
        combos.add(new WinCombo(2, 4, 6));
    }

    public void score(int index, PlayerType type) {
        combos.forEach(combo -> combo.tryAddScore(type.score, index));
    }

    public PlayerType checkWinner() {
        return StreamSupport.stream(combos.spliterator(), false)
                .map(WinCombo::getWinner)
                .filter(type -> !type.equals(PlayerType.NONE))
                .findFirst()
                .orElse(PlayerType.NONE);
    }

    public IntArray getWinningIndices() {
        return StreamSupport.stream(combos.spliterator(), false)
                .filter(WinCombo::hasWinner)
                .map(WinCombo::getIndices)
                .findFirst()
                .orElse(new IntArray());
    }
}
