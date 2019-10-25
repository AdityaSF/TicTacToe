package adiitya.tictactoe.screens;

import adiitya.tictactoe.Cell;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Timer.Task;

public class WinTask extends Task {

    private final int index;
    private final Array<Cell> cells;
    private IntArray indices;

    public WinTask(IntArray indices, int index, Array<Cell> cells) {
        this.indices = indices;
        this.index = index;
        this.cells = cells;
    }

    @Override
    public void run() {
        cells.get(indices.get(index))
                .win();
    }
}
