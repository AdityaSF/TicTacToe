package adiitya.tictactoe.screens;

import adiitya.tictactoe.Cell;
import adiitya.tictactoe.Cell.CellType;
import adiitya.tictactoe.PlayerType;
import adiitya.tictactoe.Resources;
import adiitya.tictactoe.TicTacToe;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayScreen implements Screen, InputProcessor {

	private final TicTacToe ttt;

	private TextureRegion dividers;
	private Array<Cell> cells;

	private PlayerType playerType;

	public PlayScreen(TicTacToe ttt) {
		this.ttt = ttt;
	}

	@Override
	public void show() {

		dividers = Resources.getTexture("board_dividers");

		cells = new Array<>();
		cells.add(new Cell(117, 109, CellType.CORNER));
		cells.add(new Cell(211, 109, CellType.TOP));
		cells.add(new Cell(297, 109, CellType.CORNER));
		cells.add(new Cell(117, 211, CellType.SIDE));
		cells.add(new Cell(211, 211, CellType.CENTER));
		cells.add(new Cell(297, 211, CellType.SIDE));
		cells.add(new Cell(117, 305, CellType.CORNER));
		cells.add(new Cell(211, 305, CellType.TOP));
		cells.add(new Cell(297, 305, CellType.CORNER));

		playerType = PlayerType.X;

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {

		float scale = TicTacToe.SCALE;

		ttt.batch.draw(dividers, 14F * scale, 13F * scale, 35F * scale, 38F * scale);
		cells.forEach(c -> c.render(ttt.batch));
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

		for (Cell cell : cells) {

			if (cell.onClick(screenX, screenY, playerType))
				playerType = PlayerType.flip(playerType);
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
