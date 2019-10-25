package adiitya.tictactoe.screens;

import adiitya.tictactoe.TicTacToe;
import adiitya.tictactoe.Cell;
import adiitya.tictactoe.CellType;
import adiitya.tictactoe.PlayerType;
import adiitya.tictactoe.Resources;
import adiitya.tictactoe.score.ScoreManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Timer;

public class PlayScreen implements Screen, InputProcessor {

	private final TicTacToe ttt;

	private TextureRegion dividers;
	private Array<Cell> cells;

	private PlayerType playerType;
	private ScoreManager manager;

	private boolean win;

	public PlayScreen(TicTacToe ttt) {
		this.ttt = ttt;
	}

	@Override
	public void show() {

		dividers = Resources.getTexture("Dividers");

		cells = new Array<>();
		cells.add(new Cell(117, 93, CellType.TOP_LEFT));
		cells.add(new Cell(211, 93, CellType.TOP));
		cells.add(new Cell(297, 93, CellType.TOP_RIGHT));
		cells.add(new Cell(117, 188, CellType.LEFT));
		cells.add(new Cell(211, 188, CellType.CENTER));
		cells.add(new Cell(297, 188, CellType.RIGHT));
		cells.add(new Cell(117, 289, CellType.BOTTOM_LEFT));
		cells.add(new Cell(211, 289, CellType.BOTTOM));
		cells.add(new Cell(297, 289, CellType.BOTTOM_RIGHT));

		playerType = PlayerType.X;
		manager = new ScoreManager();

		win = false;

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {

		float scale = TicTacToe.SCALE;

		ttt.batch.draw(dividers, 14F * scale, 13F * scale, 35F * scale, 38F * scale);
		cells.forEach(c -> c.render(ttt.batch));
	}

	private void handleWin() {

		IntArray winningCells = manager.getWinningIndices();

		for (int i = 0; i < 3; i++)
			Timer.schedule(new WinTask(winningCells, i, cells), 0.1F * i);

		win = true;
	}

	@Override
	public void resize(int width, int height) {
		// resize logic is handled in TicTacToe
	}

	@Override
	public void pause() {
		// unused in desktop
	}

	@Override
	public void resume() {
		// unused in desktop
	}

	@Override
	public void hide() {
		// unused in desktop
	}

	@Override
	public void dispose() {
		// no disposable resources
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		if (win)
			return false;

		for (int i = 0; i < cells.size; i++) {

			Cell cell = cells.get(i);

			if (cell.onClick(screenX, screenY, playerType)) {

				manager.score(i, playerType);
				playerType = PlayerType.flip(playerType);

				PlayerType winner = manager.checkWinner();

				if (!winner.equals(PlayerType.NONE))
					handleWin();

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
