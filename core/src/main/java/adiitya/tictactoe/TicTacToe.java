package adiitya.tictactoe;

import adiitya.tictactoe.Cell.CellType;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.graphics.GL20.*;

public class TicTacToe implements ApplicationListener, InputProcessor {

	OrthographicCamera cam;
	TextureAtlas atlas;
	SpriteBatch batch;

	TextureRegion dividers;
	Array<Cell> cells;
	Animation<AtlasRegion> oAnim;
	Animation<AtlasRegion> xAnim;
	float elapsed = 0;
	public float SCALE;

	PlayerType playerType;

	@Override
	public void create () {

		cam = new OrthographicCamera();
		atlas = new TextureAtlas(Gdx.files.internal("game.atlas"), true);
		batch = new SpriteBatch();

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

		Array<AtlasRegion> oFrames = atlas.findRegions("o");
		Array<AtlasRegion> xFrames = atlas.findRegions("x");
		oAnim = new Animation<>(1F / oFrames.size, oFrames, Animation.PlayMode.LOOP);
		xAnim = new Animation<>(1F / xFrames.size, xFrames, Animation.PlayMode.LOOP);

		playerType = PlayerType.X;
		SCALE = 500F / 64F;

		Gdx.input.setInputProcessor(this);
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

		Gdx.gl.glClearColor(0.149F, 0.169F, 0.267F, 0);
		Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		elapsed += Gdx.graphics.getDeltaTime();

		batch.begin();
		batch.draw(dividers, 14F * SCALE, 13F * SCALE, 35F * SCALE, 38F * SCALE);
		cells.forEach(c -> c.render(batch));
		batch.end();
	}

	private void drawAnimation(SpriteBatch batch, Animation<AtlasRegion> anim, float x, float y, float elapsed, boolean flip) {

		AtlasRegion texture = anim.getKeyFrame(elapsed);
		RenderUtils.renderFlipable(batch, texture, x, y, texture.getRegionWidth(), texture.getRegionHeight(), SCALE, flip);
	}

	@Override
	public void pause() {
		//unused
	}

	@Override
	public void resume() {
		//unused
	}

	@Override
	public void dispose () {
		atlas.dispose();
		batch.dispose();
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
