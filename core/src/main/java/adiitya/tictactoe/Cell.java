package adiitya.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cell {

	private static final float SCALE = 500F / 64F;
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));

	private float x;
	private float y;
	private CellType type;

	private TextureRegion player = null;

	public Cell(float x, float y, CellType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public void render(SpriteBatch batch) {
		render(batch, x, y, type);
	}

	public void render(SpriteBatch batch, float x, float y, CellType type) {
		TextureRegion texture = atlas.findRegion(type.textureName);
		batch.draw(texture, x, y, texture.getRegionWidth() * SCALE, texture.getRegionHeight() * SCALE);
	}

	public enum CellType {

		CORNER("cell_corner"),
		SIDE("cell_side"),
		TOP("cell_top"),
		CENTER("cell_center");

		private String textureName;

		CellType(String textureName) {
			this.textureName = textureName;
		}
	}
}
