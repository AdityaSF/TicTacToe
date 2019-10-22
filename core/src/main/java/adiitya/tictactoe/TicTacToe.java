package adiitya.tictactoe;

import adiitya.tictactoe.screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.badlogic.gdx.graphics.GL20.*;

public class TicTacToe extends Game {

	public OrthographicCamera cam;
	public TextureAtlas atlas;
	public SpriteBatch batch;

	public static final float SCALE = 500F / 64F;

	@Override
	public void create () {

		cam = new OrthographicCamera();
		atlas = new TextureAtlas(Gdx.files.internal("game.atlas"), true);
		batch = new SpriteBatch();

		setScreen(new PlayScreen(this));
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

		batch.begin();
		getScreen().render(Gdx.graphics.getDeltaTime());
		batch.end();
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
}
