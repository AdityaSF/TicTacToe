package adiitya.tictactoe;

import adiitya.tictactoe.Cell.CellType;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.graphics.GL20.*;

public class TicTacToe implements ApplicationListener {

	OrthographicCamera cam;
	TextureAtlas atlas;
	SpriteBatch batch;

	TextureRegion bg;
	TextureRegion dividers;
	Array<Cell> cells;

	@Override
	public void create () {

		cam = new OrthographicCamera();
		atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));
		batch = new SpriteBatch();

		bg = atlas.findRegion("board_bg");
		dividers = atlas.findRegion("board_dividers");

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
	}

	@Override
	public void resize (int width, int height) {

		cam.setToOrtho(true, width, height);
		cam.update();
		cam.position.set(250, 250, 0);

		batch.setProjectionMatrix(cam.combined);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		float dividerScale = Gdx.graphics.getWidth() / 64F;

		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(dividers, 14F * dividerScale, 13F * dividerScale, 35F * dividerScale, 38F * dividerScale);
		cells.forEach(c -> c.render(batch));
		batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		atlas.dispose();
		batch.dispose();
	}
}
